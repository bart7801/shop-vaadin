package com.bart.shopvaadin.product.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.bart.shopvaadin.category.model.CategoriesService;
import com.bart.shopvaadin.product.model.ProductsService;

@Route("add-product")
public class AddProductView extends VerticalLayout {

    private ProductForm productForm;

    public AddProductView(ProductsService productsService, CategoriesService categoriesService) {
        productForm = new ProductForm(productsService, categoriesService);
        add(productForm);
    }

}
