package com.example.grubster.controller;

import com.example.grubster.entity.ShoppingCart;
import com.example.grubster.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/all")
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartService.getAllShoppingCarts();
    }

    @PostMapping("/add")
    public ShoppingCart createShoppingCart(@RequestParam("userId") long userId) {
        return shoppingCartService.createShoppingCart(userId);
    }

    @GetMapping("/{id}")
    public ShoppingCart findShoppingCartById(@PathVariable long id) {
        return shoppingCartService.findShoppingCartById(id);
    }

    @PutMapping("/addproducttocart")
    public ShoppingCart addProductToShoppingCart(@RequestParam("cartId") long cartId, @RequestParam("productId") long productId) {
        return shoppingCartService.addToShoppingCart(cartId, productId);
    }
}
