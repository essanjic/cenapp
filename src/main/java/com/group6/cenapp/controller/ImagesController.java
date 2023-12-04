package com.group6.cenapp.controller;

import com.group6.cenapp.model.entity.Image;
import com.group6.cenapp.repository.ImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImagesController {

    private final ImageRepository imageRepository;

    public ImagesController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping
    public ResponseEntity<Image> create(@RequestBody Image image) throws IOException {
        imageRepository.save();
        return ResponseEntity.ok(image);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageRepository.findById(id);

        if (imageOptional.isPresent()) {
            Image image = imageOptional.get(); // Si existe, retorna 200
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.notFound().build(); // Si no existe, retorna 404
        }
    }
}

