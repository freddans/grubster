package com.example.grubster.controller;

import com.example.grubster.entity.Cart;
import com.example.grubster.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/all")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PostMapping("/add")
    public Cart createCart(@RequestParam("userId") long userId) {
        return cartService.createCart(userId);
    }

    @GetMapping("/{id}")
    public Cart findCartById(@PathVariable long id) {
        return cartService.findCartById(id);
    }

    @PutMapping("/addproducttocart")
    public Cart addProductToCart(@RequestParam("cartId") long cartId, @RequestParam("productId") long productId) {
        return cartService.addToCart(cartId, productId);
    }
}
