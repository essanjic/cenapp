package com.group6.cenapp.controller;

import com.group6.cenapp.model.entity.Category;
import com.group6.cenapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> listarCategorias() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Integer id) {
        Optional<Category> categorySearch = categoryService.searchCategory(id);
        return categorySearch.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Category> crearCategoria(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @PutMapping("/update")
    public ResponseEntity<?> editarCategoria(@RequestBody Category category) throws Exception {
        Optional<Category> categoriaBuscada = categoryService.searchCategory(category.getCategory());
        if (categoriaBuscada.isPresent()) {
            return ResponseEntity.ok(categoryService.updateCategory(category));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La categoría con ID: " + category.getCategory() + " no se encuentra registrada");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Integer id) {
        if (categoryService.searchCategory(id).isPresent()) {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Se eliminó con éxito la categoría con ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la categoría con ID: " + id);
    }

}
