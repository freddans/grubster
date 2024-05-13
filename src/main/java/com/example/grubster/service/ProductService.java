package com.example.grubster.service;

import com.example.grubster.entity.Product;
import com.example.grubster.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private Logger logger = Logger.getLogger(ProductService.class);
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {

        Product newProduct = new Product(product.getName(), product.getPrice());

        productRepository.save(newProduct);

        logger.info("\nAdmin added product\n");

        return product;
    }

    public Product findProductById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {

            Product product = optionalProduct.get();

            return product;
        } else {

            return null;
        }
    }

    public Product updateProduct(long id, Product newProductInformation) {

        Product existingProduct = findProductById(id);

        if (existingProduct != null) {

            if (!newProductInformation.getName().contains(existingProduct.getName()) && newProductInformation.getName() != null) {

                existingProduct.setName(newProductInformation.getName());
            }
            if (newProductInformation.getPrice() != existingProduct.getPrice() && newProductInformation.getPrice() != 0) {

                existingProduct.setPrice(newProductInformation.getPrice());
            }

            productRepository.save(existingProduct);

            return existingProduct;
        } else {

            return null;
        }
    }

    public String deleteProduct(long id) {
        Product productToDelete = findProductById(id);

        if (productToDelete != null) {

            logger.info("\nAdmin deleted product\n");

            productRepository.delete(productToDelete);

            return "Deleted product";
        } else {

            logger.error("\nCould not delete product\n");

            return "provided product ID does not exist";
        }
    }
}
