package com.bart.shopvaadin.category.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    @NonNull
    private CategoriesRepository categoriesRepository;

    public List<Category> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public void addCategory(Category category) {
        categoriesRepository.save(category);
    }

}
