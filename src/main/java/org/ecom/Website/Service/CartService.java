package org.ecom.Website.Service;

import org.ecom.Website.Model.Cart;
import org.ecom.Website.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartById(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cartRepository.save(cart);
        }
        return cart;
    }

    public void addToCart(String userId, String productId, Integer qty) {
        Cart cart = getCartById(userId);
        cart.getProductIds().put(productId, qty);
        cartRepository.save(cart);
    }

    public void removeFromCart(String userId, String productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getProductIds().remove(productId);
            cartRepository.save(cart);
        }
    }

    public void clearCart(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getProductIds().clear();
            cartRepository.delete(cart);
        }
    }
}
