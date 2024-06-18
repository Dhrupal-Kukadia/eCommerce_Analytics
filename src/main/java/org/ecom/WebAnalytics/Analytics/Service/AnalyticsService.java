package org.ecom.WebAnalytics.Analytics.Service;

import org.ecom.WebAnalytics.ES.Repository.OrderLogRepository;
import org.ecom.WebAnalytics.ES.Repository.UserActivityLogRepository;
import org.ecom.WebAnalytics.Log.Action;
import org.ecom.WebAnalytics.Log.OrderLog;
import org.ecom.WebAnalytics.Log.UserActivityLog;
import org.ecom.Website.Model.Order;
import org.ecom.Website.Model.Product;
import org.ecom.Website.Model.User;
import org.ecom.Website.Repository.OrderRepository;
import org.ecom.Website.Repository.ProductRepository;
import org.ecom.Website.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {
    @Autowired
    private UserActivityLogRepository userActivityLogRepository;
    @Autowired
    private OrderLogRepository orderLogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Product getMostViewedProduct() {
        Map<String, Long> productViewCount = new HashMap<>();
        Iterable<UserActivityLog> logs = userActivityLogRepository.findAll();
        logs.forEach(log -> {
            if (Action.VIEWED.equals(log.getAction())) {
                String productId = log.getProductId();
                productViewCount.put(productId, productViewCount.getOrDefault(productId, 0L) + 1);
            }
        });
        String productId = productViewCount.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
        return (productId == null ? null : productRepository.findById(productId).orElse(null));
    }

    public Map<String, Product> getMostViewedProductByLocation(String locationType) {
        Map<String, Product> result = new HashMap<>();
        Iterable<UserActivityLog> logs = userActivityLogRepository.findAll();
        Map<String, Map<String, Long>> locationToProduct = new HashMap<>();

        logs.forEach(log -> {
            if (log.getAction().equals(Action.VIEWED)) {
                User user = userRepository.findById(log.getUserId()).orElse(null);
                if (user != null) {
                    String userLocation = switch (locationType) {
                        case "country" -> user.getAddress().getCountry();
                        case "state" -> user.getAddress().getState();
                        case "city" -> user.getAddress().getCity();
                        default -> "";
                    };
                    Map<String, Long> productViewCount = locationToProduct.computeIfAbsent(userLocation, k -> new HashMap<>());
                    productViewCount.put(log.getProductId(), productViewCount.getOrDefault(log.getProductId(), 0L) + 1);
                }
            }
        });

        locationToProduct.forEach((location, productViewCount) -> {
            String productId = productViewCount.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
            Product product = productId == null ? null : productRepository.findById(productId).orElse(null);
            if (product != null) {
                result.put(location, product);
            }
        });
        return result;
    }

    public Product getMostBoughtProduct() {
        Map<String, Long> productBoughtCount = new HashMap<>();
        Iterable<Order> orders = orderRepository.findAll();
        orders.forEach(order -> {
            order.getProductIds().forEach((productId, qty) -> {
                productBoughtCount.put(productId, productBoughtCount.getOrDefault(productId, 0L) + qty);
            });
        });
        String productId = productBoughtCount.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
        return (productId == null ? null : productRepository.findById(productId).orElse(null));
    }

    public Map<String, Product> getMostBoughtProductByLocation(String locationType) {
        Map<String, Product> result = new HashMap<>();
        Iterable<OrderLog> logs = orderLogRepository.findAll();
        Map<String, List<String>> locationToUser = new HashMap<>();

        logs.forEach(log -> {
            Order order = orderRepository.findById(log.getOrderId()).orElse(null);
            if (order != null) {
                User user = userRepository.findById(order.getUserId()).orElse(null);
                if (user != null) {
                    String userLocation = switch (locationType) {
                        case "country" -> user.getAddress().getCountry();
                        case "state" -> user.getAddress().getState();
                        case "city" -> user.getAddress().getCity();
                        default -> "";
                    };
                    locationToUser.computeIfAbsent(userLocation, list -> new ArrayList<>()).add(user.getId());
                }
            }
        });

        locationToUser.forEach((location, userIds) -> {
            Map<String, Long> productOrderCount = new HashMap<>();
            userIds.forEach(userId -> {
                List<OrderLog> userOrderLog = orderLogRepository.findByuserId(userId);
                userOrderLog.forEach(orderLog -> {
                    Order order = orderRepository.findById(orderLog.getId()).orElse(null);
                    if (order != null) {
                        order.getProductIds().forEach((productId, qty) -> {
                            productOrderCount.put(productId, productOrderCount.getOrDefault(productId, 0L) + qty);
                        });
                    }
                });
            });
            String mostBoughtProductId = productOrderCount.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
            Product product = mostBoughtProductId == null ? null : productRepository.findById(mostBoughtProductId).orElse(null);
            if (product != null) {
                result.put(location, product);
            }
        });
        return result;
    }

    public Long getTotalOrder(Long sinceTime, Long untilTime) {
        return orderRepository.countByTimestampBetween(sinceTime, untilTime);
    }

    public double getTotalSales(Long sinceTime, Long untilTime) {
        Iterable<Order> orders = orderRepository.findByTimestampBetween(sinceTime, untilTime);
        double sales = 0;
        for (Order order : orders) {
            sales += order.getTotal();
        }
        return sales;
    }
}
