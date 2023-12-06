package com.group6.cenapp.service;

import com.group6.cenapp.exception.ResourceNotFoundException;
import com.group6.cenapp.model.entity.Image;
import com.group6.cenapp.model.entity.Restaurant;
import com.group6.cenapp.repository.ImageRepository;
import com.group6.cenapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final RestaurantRepository restaurantRepository;
    private final ImageConverterAndDatabaseOperator imageConverter;

    @Autowired
    public ImageService(ImageRepository imageRepository, RestaurantRepository restaurantRepository, ImageConverterAndDatabaseOperator imageConverter) {
        this.imageRepository = imageRepository;
        this.restaurantRepository = restaurantRepository;
        this.imageConverter = imageConverter;
    }

    public void associateImagesToRestaurant(List<Integer> imageIds, Integer restaurantId) throws Throwable {
        for (Integer imageId : imageIds) {
            persistImageAndAssociateToRestaurant(imageId, restaurantId);
        }
    }

    private void persistImageAndAssociateToRestaurant(Integer imageId, Integer restaurantId) throws Throwable {
        Image image = (Image) imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("Image not found"));

        byte[] imageBytes = imageConverter.convertImageToBytes(new File(Arrays.toString(image.getImages())));

        Restaurant restaurant = restaurantRepository.findById(Math.toIntExact(restaurantId)).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        restaurant.setImage(Arrays.toString(imageBytes));
        restaurantRepository.save(restaurant);
    }

    public List<Image> listImages() {
        return imageRepository.findAll();
    }

    public Image saveImage(Image image) {

        return imageRepository.save(image);
    }
}

