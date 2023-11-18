package com.group6.cenapp.service;
import com.group6.cenapp.model.Category;
import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Restaurant;
import com.group6.cenapp.repository.RestaurantRepository;
import com.group6.cenapp.service.implement.RestaurantServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService implements RestaurantServiceInt {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> getRestaurantById(Integer id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurantById(Integer id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public List<Restaurant> getRestaurantByCategory(Category id) {
        return restaurantRepository.getByCategory(id);
    }

    public List<Restaurant> getRestaurantByCity(City id)  { return restaurantRepository.getByCity(id);
    }

    @Override
    public List<Restaurant> getRestaurantByRangeDate(LocalDate check_in_date, LocalDate check_out_date) {
        //System.out.println(check_in_date + " --- " + check_out_date);
        return restaurantRepository.getByRangeDate(check_in_date,check_out_date);
    }

    @Override
    public List<Restaurant> getRestaurantByCityAndRangeDate(Integer city_id, LocalDate check_in_date, LocalDate check_out_date) {
        return restaurantRepository.getByCityAndRangeDate(city_id, check_in_date,check_out_date);
    }
    @Override
    public List<Restaurant> getRandomRestaurant() {
        return restaurantRepository.getRandomRestaurant();
    }
}
