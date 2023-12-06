package com.group6.cenapp.controller;

import com.group6.cenapp.model.entity.Image;
import com.group6.cenapp.repository.ImageRepository;
import com.group6.cenapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/v1/api/images")
public class ImagesController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;
    public ImagesController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            imageService.saveImage((Image) file);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo cargar el archivo");
        }
        return ResponseEntity.ok("Archivo cargado exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Integer id) {
        Optional imageOptional = imageRepository.findById(id);

        if (imageOptional.isPresent()) {
            Image image = (Image) imageOptional.get(); // Si existe, retorna 200
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.notFound().build(); // Si no existe, retorna 404
        }
    }
}

