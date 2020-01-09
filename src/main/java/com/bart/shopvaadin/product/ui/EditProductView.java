package com.bart.shopvaadin.product.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.bart.shopvaadin.category.model.CategoriesService;
import com.bart.shopvaadin.product.model.ProductsService;

@Route("products")
public class EditProductView extends VerticalLayout implements HasUrlParameter<Long>, AfterNavigationObserver {

    private ProductForm productForm;
    private Long productId;

    public EditProductView(ProductsService productsService, CategoriesService categoriesService) {
        productForm = new ProductForm(productsService, categoriesService);
        add(productForm);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long productId) {
        this.productId = productId;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        productForm.loadProduct(productId);
    }

}
