package com.bart.shopvaadin.product.ui;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.grid.Grid;
import lombok.Getter;
import com.bart.shopvaadin.product.model.Product;

public class ProductSelectedEvent extends ComponentEvent<Grid<Product>> {

    @Getter
    private Product product;

    public ProductSelectedEvent(Grid<Product> source, Product product) {
        super(source, false);
        this.product = product;
    }

}
