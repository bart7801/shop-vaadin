package com.bart.shopvaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import com.bart.shopvaadin.product.ui.ProductsView;

@Route
public class MainView extends VerticalLayout {

    private HorizontalLayout menu = new HorizontalLayout();

    public MainView() {
        Router router = UI.getCurrent().getRouter();
        Anchor productsAnchor = new Anchor(router.getUrl(ProductsView.class), "Produkty");
        menu.add(productsAnchor);
        add(menu);
    }

}
