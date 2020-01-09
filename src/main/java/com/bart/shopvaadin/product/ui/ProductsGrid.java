package com.bart.shopvaadin.product.ui;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.shared.Registration;
import com.bart.shopvaadin.product.model.Product;
import com.bart.shopvaadin.product.model.ProductsService;

public class ProductsGrid extends Grid<Product> {

    private ProductsService productsService;

    public ProductsGrid(ProductsService productsService) {
        this.productsService = productsService;
        initGrid();
    }

    private void initGrid() {
        addColumn(Product::getName).setHeader("Name");
        addColumn(Product::getDescription).setHeader("Description");
        addColumn(Product::getQuantity).setHeader("Quantity");
        addColumn(Product::getPrice).setHeader("Price");
        asSingleSelect().addValueChangeListener(event -> fireEvent(new ProductSelectedEvent(this, event.getValue())));
    }

    public Registration addProductSelectionListener(ComponentEventListener<ProductSelectedEvent> listener) {
        return addListener(ProductSelectedEvent.class, listener);
    }

    public void refresh() {
        setItems(productsService.getAllProducts());
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        refresh();
    }

}
