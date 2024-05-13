package com.example.grubster.controller;

import com.example.grubster.entity.Product;
import com.example.grubster.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable long id) {
        return productService.findProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product newProductInformation) {
        return productService.updateProduct(id, newProductInformation);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        return productService.deleteProduct(id);
    }
}
