package com.bart.shopvaadin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bart.shopvaadin.category.model.CategoriesService;
import com.bart.shopvaadin.category.model.Category;
import com.bart.shopvaadin.product.model.Product;
import com.bart.shopvaadin.product.model.ProductsService;

import java.util.Date;

@SpringBootApplication
public class Application {

    public Application(ProductsService productsService, CategoriesService categoriesService) {
        categoriesService.addCategory(new Category("Warzywa"));
        categoriesService.addCategory(new Category("Owoce"));
        categoriesService.addCategory(new Category("Jajka"));

        productsService.addProduct(Product.builder()
            .name("Pietruszka")
            .description("dobra, zdrowa, świeża, bez konserwantów")
            .quantity(10)
            .price(100)
            .availableSince(new Date())
            .build());

        productsService.addProduct(Product.builder()
                .name("pomidor")
                .description("soczysty, zdrowt")
                .quantity(11)
                .price(12)
                .availableSince(new Date())
                .build());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
