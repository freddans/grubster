package com.example.grubster.service;

import com.example.grubster.entity.ShoppingCart;
import com.example.grubster.entity.Product;
import com.example.grubster.entity.User;
import com.example.grubster.repository.ShoppingCartRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private Logger logger = Logger.getLogger(ShoppingCartService.class);
    private ShoppingCartRepository shoppingCartRepository;
    private UserService userService;
    private ProductService productService;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, UserService userService, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart createShoppingCart(long userId) {
        User user = userService.findUserById(userId);


        if (user != null) {

            ShoppingCart newShoppingCart = new ShoppingCart(user);

            shoppingCartRepository.save(newShoppingCart);

            return newShoppingCart;
        } else {

            return null;
        }
    }

    public ShoppingCart findShoppingCartById(long id) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findById(id);

        if (optionalCart.isPresent()) {
            ShoppingCart shoppingCart = optionalCart.get();

            return shoppingCart;
        } else {

            return null;
        }
    }

    public ShoppingCart addToShoppingCart(long shoppingCartId, long productId) {
        ShoppingCart shoppingCart = findShoppingCartById(shoppingCartId);
        Product product = productService.findProductById(productId);

        if (shoppingCart != null && product != null) {

            shoppingCart.addToShoppingCart(product);

            product.setQuantity(product.getQuantity() -1);

            productService.updateProduct(productId, product);

            shoppingCartRepository.save(shoppingCart);

            return shoppingCart;
        } else {

            return null;
        }

    }
}
