package com.group6.cenapp.controller;

import com.group6.cenapp.model.entity.Image;
import com.group6.cenapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/api/images")
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("nombre") String nombre, @RequestParam("archivo") MultipartFile archivo) {
        try {
            Image image = imageService.guardarImagen(nombre, archivo);
            return ResponseEntity.ok("Imagen subida con éxito. ID: " + image.getImage_id());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al subir la imagen.");
        }
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
        byte[] imageBytes = imageService.obtainBytesImageById(id);

        if (imageBytes != null) {
            HttpHeaders headers = new HttpHeaders();


            String contentType = determineContentType(imageBytes);
            headers.setContentType(MediaType.parseMediaType(contentType));

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    private String determineContentType(byte[] imageBytes) {

        if (startsWithJpegSignature(imageBytes)) {
            return "image/jpeg";
        } else {
            return "image/png";
        }
    }


    private boolean startsWithJpegSignature(byte[] bytes) {
        return bytes.length >= 2 && bytes[0] == (byte) 0xFF && bytes[1] == (byte) 0xD8;
    }
}
