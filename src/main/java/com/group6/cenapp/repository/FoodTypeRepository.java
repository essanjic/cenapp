package com.group6.cenapp.repository;

import com.group6.cenapp.model.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
    @Override
    List<FoodType> findAllById(Iterable<Integer> integers);
}
