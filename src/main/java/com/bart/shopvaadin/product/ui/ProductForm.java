package com.bart.shopvaadin.product.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.LocalDateToDateConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.bart.shopvaadin.category.model.CategoriesService;
import com.bart.shopvaadin.category.model.Category;
import com.bart.shopvaadin.extension.MinLengthValidator;
import com.bart.shopvaadin.product.model.Product;
import com.bart.shopvaadin.product.model.ProductsService;

public class ProductForm extends FormLayout {

    private TextField nameField = new TextField();
    private TextField descriptionField = new TextField();
    private TextField priceField = new TextField();
    private DatePicker availableSinceField = new DatePicker();
    private TextField quantityField = new TextField();
    private ComboBox<Category> categoryField = new ComboBox<>();
    private HorizontalLayout buttonsLayout = new HorizontalLayout();
    private Button cancelButton = new Button();
    private Button saveButton = new Button();

    private ProductsService productsService;
    private CategoriesService categoriesService;
    private Binder<Product> binder = new Binder<>(Product.class);
    private Product product = new Product();

    public ProductForm(ProductsService productsService, CategoriesService categoriesService) {
        this.productsService = productsService;
        this.categoriesService = categoriesService;
        initFields();
        initButtons();
        bindModel();
    }

    private void initFields() {
        nameField.setLabel("Name");
        descriptionField.setLabel("Description");
        priceField.setLabel("Price");
        availableSinceField.setLabel("Available since");
        quantityField.setLabel("Quantity");
        categoryField.setItemLabelGenerator(Category::getName);
        categoryField.setItems(categoriesService.getAllCategories());
        categoryField.setLabel("Category");
        add(nameField, descriptionField, priceField, availableSinceField, quantityField, categoryField);
    }

    private void initButtons() {
        cancelButton.setText("Cancel");
        cancelButton.addClickListener(event -> showProducts());
        saveButton.setText("Save");
        saveButton.addClickListener(clickEvent -> saveProduct());
        buttonsLayout.add(cancelButton, saveButton);
        add(buttonsLayout);
    }

    private void saveProduct() {
        binder.writeBeanIfValid(product);
        if (binder.isValid()) {
            productsService.addProduct(product);
            showProducts();
        }
    }

    private void bindModel() {
        binder.forField(nameField)
                .asRequired("Field is required")
                .bind(Product::getName, Product::setName);
        binder.forField(descriptionField)
                .withValidator(new MinLengthValidator(3))
                .bind(Product::getDescription, Product::setDescription);
        binder.forField(priceField)
                .withConverter(new StringToLongConverter("Invalid price"))
                .withValidator(price -> price > 0, "Price is to low")
                .bind(Product::getPrice, Product::setPrice);
        binder.forField(availableSinceField)
                .withConverter(new LocalDateToDateConverter())
                .bind(Product::getAvailableSince, Product::setAvailableSince);
        binder.forField(quantityField)
                .withConverter(new StringToIntegerConverter("Invalid quantity"))
                .bind(Product::getQuantity, Product::setQuantity);
        binder.forField(categoryField).bind(Product::getCategory, Product::setCategory);
    }

    private void showProducts() {
        UI.getCurrent().navigate(ProductsView.class);
    }

    public void loadProduct(Long productId) {
        product = productsService.getProduct(productId);
        binder.readBean(product);
    }

}
