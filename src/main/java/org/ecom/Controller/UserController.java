package org.ecom.Controller;

import org.ecom.Model.Cart;
import org.ecom.Model.User;
import org.ecom.Model.UserRegistrationDTO;
import org.ecom.Service.CartService;
import org.ecom.Service.OrderService;
import org.ecom.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

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
        orderService.order(user, cart);
        cartService.clearCart(id);
    }
}
