package org.ecom.WebAnalytics.Analytics.Controller;

import org.ecom.WebAnalytics.Analytics.Service.AnalyticsService;
import org.ecom.Website.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/web_analytics/analytics")
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/most_viewed_product")
    public Product getMostViewedProduct() {
        return analyticsService.getMostViewedProduct();
    }

    @GetMapping("/most_viewed_product/{location}")
    public Map<String, Product> getMostViewedProduct(@PathVariable String location) {
        return analyticsService.getMostViewedProductByLocation(location);
    }

    @GetMapping("/most_bought_product")
    public Product getMostBoughtProduct() {
        return analyticsService.getMostBoughtProduct();
    }

    @GetMapping("/most_bought_product/{location}")
    public Map<String, Product> getMostBoughtProduct(@PathVariable String location) {
        return analyticsService.getMostBoughtProductByLocation(location);
    }

    @GetMapping("/total_order")
    public Long getTotalOrder() {
        return analyticsService.getTotalOrder();
    }

    @GetMapping("/total_sales")
    public Double getTotalSales() {
        return analyticsService.getTotalSales();
    }
}
