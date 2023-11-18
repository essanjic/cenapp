package com.group6.cenapp.service;

import com.group6.cenapp.model.Category;
import com.group6.cenapp.service.implement.CategoryServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInt {
   public List<Category> listCategories() {
        return null;
    }

    public Optional<Category> searchCategory(Integer id) {
        return null;
    }

    public Category saveCategory(Category category) {
        return null;
    }

    public Category updateCategory(Category category) {
        return null;

    }

    public void deleteCategory(Integer id) {
    }
}
