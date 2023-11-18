package com.group6.cenapp.service.implement;

import com.group6.cenapp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInt {
    List<Category> listCategories();
    Optional<Category> searchCategory(Integer id);
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Integer id);
}
