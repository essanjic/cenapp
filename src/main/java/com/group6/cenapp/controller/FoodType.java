package com.group6.cenapp.controller;


import com.group6.cenapp.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/v1/api/foodtypes")
public class FoodType {

    @Autowired
    private FoodTypeService foodTypeService;

    @GetMapping
    public ResponseEntity<List<com.group6.cenapp.model.entity.FoodType>> listFoodType(){
        return ResponseEntity.ok(foodTypeService.listFoodType());
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.group6.cenapp.model.entity.FoodType> findFoodTypeById(@PathVariable Integer id) {
        Optional<com.group6.cenapp.model.entity.FoodType> categorySearch = foodTypeService.searchFoodType(id);
        return categorySearch.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping("/create")
    public ResponseEntity<com.group6.cenapp.model.entity.FoodType> createFoodType(@RequestBody com.group6.cenapp.model.entity.FoodType foodType){
        return ResponseEntity.ok(foodTypeService.saveFoodType(foodType));
    }

    @PutMapping("/update")
    public ResponseEntity<?> editFoodType(@RequestBody com.group6.cenapp.model.entity.FoodType foodType){
        Optional<com.group6.cenapp.model.entity.FoodType> categoriaBuscada = foodTypeService.searchFoodType(foodType.getFood_type_id());
        if(categoriaBuscada.isPresent()){
            return ResponseEntity.ok(foodTypeService.updateFoodType(foodType));
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El tipo de comida con ID: " + foodType.getFood_type_id() + " no se encuentra registrado");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFoodType(@PathVariable Integer id){
        if(foodTypeService.searchFoodType(id).isPresent()){
            foodTypeService.deleteFoodType(id);
            return ResponseEntity.ok("Se eliminó con éxito el tipo de comida con ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el tipo de comida con ID: " + id);
    }


}
