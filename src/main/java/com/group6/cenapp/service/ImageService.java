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

    public void associateImagesToRestaurant(List<Long> imageIds, Long restaurantId) {
        for (Long imageId : imageIds) {
            persistImageAndAssociateToRestaurant(imageId, restaurantId);
        }
    }

    private void persistImageAndAssociateToRestaurant(Long imageId, Long restaurantId) throws Throwable {
        Image image = (Image) imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("Image not found"));

        byte[] imageBytes = imageConverter.convertImageToBytes(new File(Arrays.toString(image.getImage())));

        Restaurant restaurant = restaurantRepository.findById(Math.toIntExact(restaurantId)).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        restaurant.setImage(Arrays.toString(imageBytes));
        restaurantRepository.save(restaurant);
    }
}

