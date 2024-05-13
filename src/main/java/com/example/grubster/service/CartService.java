package com.example.grubster.service;

import com.example.grubster.entity.Cart;
import com.example.grubster.entity.Product;
import com.example.grubster.entity.User;
import com.example.grubster.repository.CartRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private Logger logger = Logger.getLogger(CartService.class);
    private CartRepository cartRepository;
    private UserService userService;
    private ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart createCart(long userId) {
        User user = userService.findUserById(userId);


        if (user != null) {

            Cart newCart = new Cart(user);

            cartRepository.save(newCart);

            return newCart;
        } else {

            return null;
        }
    }

    public Cart findCartById(long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            return cart;
        } else {

            return null;
        }
    }

    public Cart addToCart(long cartId, long productId) {
        Cart cart = findCartById(cartId);
        Product product = productService.findProductById(productId);

        if (cart != null && product != null) {

            cart.addToCart(product);

            product.setQuantity(product.getQuantity() -1);

            productService.updateProduct(productId, product);

            cartRepository.save(cart);

            return cart;
        } else {

            return null;
        }

    }
}
