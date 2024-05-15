package com.example.grubster.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shoppingcarts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "shoppingcart_products",
            joinColumns = @JoinColumn(name = "shoppingcart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productShoppingCart;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.user = user;
        this.productShoppingCart = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductShoppingCart() {
        return productShoppingCart;
    }

    public void setProductShoppingCart(List<Product> productShoppingCart) {
        this.productShoppingCart = productShoppingCart;
    }

    public void addToShoppingCart(Product product) {
        productShoppingCart.add(product);
    }

    public void removeFromShoppingCart(Product product) {
        productShoppingCart.remove(product);
    }
}
