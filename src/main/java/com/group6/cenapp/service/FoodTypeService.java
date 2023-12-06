package com.group6.cenapp.service;

import com.group6.cenapp.model.entity.FoodType;
import com.group6.cenapp.repository.FoodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodTypeService {

    @Autowired
    private FoodTypeRepository foodTypeRepository;



    public List<FoodType> listFoodType() {
        return foodTypeRepository.findAll();
    }

    public List<FoodType> listFoodTypesById(Iterable<Integer> integers) {
        return foodTypeRepository.findAllById(integers);
    }


    public Optional<FoodType> searchFoodType(Integer id) {
        return foodTypeRepository.findById(id);
    }


    public FoodType saveFoodType(FoodType foodType) {
        return foodTypeRepository.save(foodType);
    }

    public FoodType updateFoodType(FoodType foodType) {
        return foodTypeRepository.save(foodType);
    }


    public void deleteFoodType(Integer id) {
        foodTypeRepository.deleteById(id);
    }
}
