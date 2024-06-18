package org.ecom.Website.Service;

import org.ecom.Website.Model.Cart;
import org.ecom.Website.Model.Order;
import org.ecom.Website.Model.Product;
import org.ecom.Website.Model.User;
import org.ecom.Website.Repository.OrderRepository;
import org.ecom.Website.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public void order(User user, Cart cart) {
        Order order = new Order();
        order.setUserId(user.getId());
        order.setProductIds(cart.getProductIds());
        totalOrderAmount(order);
        order.setTimestamp(System.currentTimeMillis());
        orderRepository.save(order);
    }

    private void totalOrderAmount(Order order) {
        double total = 0;
        for (Map.Entry<String, Integer> entry : order.getProductIds().entrySet()) {
            Product product =  productRepository.findById(entry.getKey()).orElse(null);
            if(product != null)
                total += product.getPrice();
        }
        order.setTotal(total);
    }
}
