package com.bart.shopvaadin.product.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.bart.shopvaadin.product.model.Product;
import com.bart.shopvaadin.product.model.ProductsService;

@Route("products")
public class ProductsView extends VerticalLayout {

    private ProductsGrid productsGrid;
    private HorizontalLayout buttonsLayout = new HorizontalLayout();
    private Button addButton = new Button();
    private Button editButton = new Button();
    private Button deleteButton = new Button();

    private ProductsService productsService;
    private Product selectedProduct;

    public ProductsView(ProductsService productsService) {
        this.productsService = productsService;
        initGrid();
        initButtons();
    }

    private void initGrid() {
        productsGrid = new ProductsGrid(productsService);
        productsGrid.addProductSelectionListener(event -> changeSelection(event.getProduct()));
        add(productsGrid);
    }

    private void initButtons() {
        UI ui = UI.getCurrent();
        addButton.setText("Add");
        addButton.addClickListener(event -> ui.navigate(AddProductView.class));
        editButton.setText("Edit");
        editButton.setVisible(false);
        editButton.addClickListener(event -> {
            String url = String.format("products/%d", selectedProduct.getId());
            ui.navigate(url);
        });
        deleteButton.setText("Delete");
        deleteButton.setVisible(false);
        deleteButton.addClickListener(event -> deleteProduct());
        buttonsLayout.add(addButton, editButton, deleteButton);
        add(buttonsLayout);
    }

    private void changeSelection(Product product) {
        editButton.setVisible(product != null);
        deleteButton.setVisible(product != null);
        selectedProduct = product;
    }

    private void deleteProduct() {
        productsService.deleteProduct(selectedProduct);
        productsGrid.refresh();
    }

}
