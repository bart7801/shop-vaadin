package com.bart.shopvaadin.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.bart.shopvaadin.category.model.Category;

import javax.persistence.*;
import java.util.Date;

@Table(name = "products")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String description;
    private long price;
    @Temporal(TemporalType.DATE)
    private Date availableSince;
    private int quantity;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

}
