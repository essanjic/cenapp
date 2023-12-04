package com.group6.cenapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository<Image> extends JpaRepository<Image, Long> {
    void save();
    Optional<Image> findById(Long id);
}
