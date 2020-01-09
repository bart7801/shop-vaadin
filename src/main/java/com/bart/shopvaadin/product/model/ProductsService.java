package com.bart.shopvaadin.product.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    @NonNull
    private ProductsRepository productsRepository;

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public Product getProduct(Long productId) {
        return productsRepository.getById(productId);
    }

    public void updateProduct(Product product) {
        productsRepository.save(product);
    }

    public void addProduct(Product product) {
        updateProduct(product);
    }

    public long getProductsCount() {
        return productsRepository.count();
    }

    public void decreaseProductQuantity(Long productId) {
        productsRepository.decreaseQuantity(productId);
    }

    public void deleteProduct(Product product) {
        productsRepository.delete(product);
    }

}
