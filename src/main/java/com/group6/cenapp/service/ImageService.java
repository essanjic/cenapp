package com.group6.cenapp.service;

import com.group6.cenapp.model.entity.Image;
import com.group6.cenapp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(String nombre, MultipartFile archivo) throws IOException {
        byte[] bytes = archivo.getBytes();
        Image image = new Image();
        image.setName(nombre);
        image.setContain(bytes);
        return imageRepository.save(image);
    }

    public Optional<Image> getImageById(Integer id) {
        return imageRepository.findById(id);
    }

    public byte[] obtainBytesImageById(Integer id) {
        Optional<Image> optionalImagen = getImageById(id);
        return optionalImagen.map(Image::getContain).orElse(null);
    }




}
