package com.group6.cenapp.service;

import com.group6.cenapp.model.entity.Restaurant;
import com.group6.cenapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;


    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    /*public List<Restaurant>getRestaurantByCategory(Category id){
        return restaurantRepository.get(id);
    };*/


    public Optional<Restaurant> getRestaurantById(Integer id) {
        return restaurantRepository.findById(id);
    }



    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


    public Restaurant updateRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


    public void deleteRestaurantById(Integer id) {
        restaurantRepository.deleteById(id);
    }




    public List<Restaurant> getRestaurantByRangeDate(LocalDate check_in_date, LocalDate check_out_date) {
        //System.out.println(check_in_date + " --- " + check_out_date);
        return restaurantRepository.getByRangeDate(check_in_date,check_out_date);
    }


    public List<Restaurant> getRestaurantByCityAndRangeDate(Integer city_id, LocalDate check_in_date, LocalDate check_out_date) {
        return restaurantRepository.getByCityAndRangeDate(city_id, check_in_date,check_out_date);
    }

    public List<Restaurant> getRandomRestaurant() {
        return restaurantRepository.getRandomRestaurant();
    }

    public List<Restaurant> getRestaurantByCity(Integer id) {
        return restaurantRepository.getByCity(id);
    }

}
