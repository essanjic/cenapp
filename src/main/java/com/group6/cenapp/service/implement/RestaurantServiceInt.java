package com.group6.cenapp.service.implement;

import com.group6.cenapp.model.Category;
import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RestaurantServiceInt {
    List<Restaurant> getAllRestaurants();
    Optional<Restaurant> getRestaurantById(Integer id);
    List<Restaurant>getRestaurantByCategory(Category id);
    Restaurant saveRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Restaurant restaurant);
    void deleteRestaurantById(Integer id);
    List<Restaurant>getRestaurantByCity(City id);
    List<Restaurant> getRestaurantByRangeDate(LocalDate check_in_date, LocalDate check_out_date);
    List<Restaurant> getRestaurantByCityAndRangeDate(Integer city_id, LocalDate check_in_date, LocalDate check_out_date);

    List<Restaurant> getRandomRestaurant();

}
