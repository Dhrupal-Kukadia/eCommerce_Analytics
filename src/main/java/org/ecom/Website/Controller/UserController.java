package org.ecom.Website.Controller;

import org.ecom.WebAnalytics.Log.Action;
import org.ecom.WebAnalytics.Log.OrderLog;
import org.ecom.WebAnalytics.Log.UserActivityLog;
import org.ecom.Website.Model.Cart;
import org.ecom.Website.Model.User;
import org.ecom.Website.DTO.UserRegistrationDTO;
import org.ecom.Website.Service.CartService;
import org.ecom.Website.Service.OrderService;
import org.ecom.Website.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_ACTIVITY_LOG_URL = "http://localhost:8082/analytics/kafka/publish/user_activity_log";
    private static final String ORDER_LOG_URL = "http://localhost:8082/analytics/kafka/publish/order_log";

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/name/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @PostMapping("/create")
    public void createUser(@RequestBody UserRegistrationDTO userDTO) {
        userService.createUser(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/cart")
    public Cart getCartById(@PathVariable String id) {
        Cart cart = cartService.getCartById(id);
        User user = getUserById(id);
        user.setCartId(cart.getId());
        userService.saveUser(user);
        return cart;
    }

    @PostMapping("/{userId}/cart/add")
    public void addToCart(@PathVariable("userId") String userId, @RequestParam("productId") String productId, @RequestParam("qty") Integer qty) {
        qty = qty == null ? 1 : qty;
        cartService.addToCart(userId, productId, qty);
        generateUserActivityLog(userId, productId);
    }

    @DeleteMapping("/{userId}/cart/remove")
    public void removeFromCart(@PathVariable("userId") String userId, @RequestParam("productId") String productId) {
        cartService.removeFromCart(userId, productId);
    }

    @DeleteMapping("/{userId}/cart/clear")
    public void clearCart(@PathVariable("userId") String userId) {
        cartService.clearCart(userId);
    }

    @PostMapping("/{id}/order")
    public void order(@PathVariable String id) {
        User user = userService.getUserById(id);
        Cart cart = cartService.getCartById(id);
        String orderId = orderService.order(user, cart);
        cartService.clearCart(id);
        generateOrderLog(id, orderId);
    }

    // <------------------------------------------ PRIVATE METHODS ---------------------------------------------------->

    private void generateUserActivityLog(String userId, String productId) {
        UserActivityLog userActivityLog = new UserActivityLog();
        userActivityLog.setUserId(userId);
        userActivityLog.setProductId(productId);
        userActivityLog.setAction(Action.ADDED);
        restTemplate.postForObject(USER_ACTIVITY_LOG_URL, userActivityLog, Void.class);
    }

    private void generateOrderLog(String userId, String orderId) {
        OrderLog orderLog = new OrderLog();
        orderLog.setUserId(userId);
        orderLog.setOrderId(orderId);
        restTemplate.postForObject(ORDER_LOG_URL, orderLog, Void.class);
    }
}
