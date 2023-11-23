package com.group6.cenapp.repository;

import com.group6.cenapp.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Override
    List<Category> findAllById(Iterable<Integer> integers);
}
