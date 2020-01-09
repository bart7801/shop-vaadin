package com.bart.shopvaadin.category.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "categories")
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @GeneratedValue
    @Id
    private Long id;
    @NonNull
    private String name;
    private String description;

}
