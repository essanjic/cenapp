package com.group6.cenapp.service;

import com.group6.cenapp.exception.BadRequestException;
import com.group6.cenapp.model.entity.FoodType;
import com.group6.cenapp.model.entity.Restaurant;
import com.group6.cenapp.repository.FoodTypeRepository;
import com.group6.cenapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;


    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }


    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    public List<Restaurant> getRestaurantByFoodType(List<FoodType> foodType) {
        return restaurantRepository.getByFoodTypesIn(foodType);
    }
    public Optional<Restaurant> getRestaurantById(Integer id) {
        return restaurantRepository.findById(id);
    }



    public Restaurant saveRestaurant(Restaurant restaurant) throws BadRequestException {
        FoodType foodType = new FoodType();
        if(restaurant.getFoodTypes() == null) {
            if(foodType.getFood_type_id() == null || foodType.getFood_type_id() <= 0){
                throw new BadRequestException("Debe ingresar un tipo de comida válido");
            }
            throw new BadRequestException("Debe ingresar un tipo de comida");
        }
        foodType.setFood_type_id(restaurant.getFoodTypes().get(0).getFood_type_id());
        foodTypeRepository.save(foodType);

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

    //Esta sera la logica para
    /*public List<Restaurant> getRestaurantByCityAndRangeDateAndTable(Integer city_id, LocalDate check_in_date, LocalDate check_out_date, Boolean tableIsAvailable
        return restaurantRepository.getByCityAndRangeDateAndTable(city_id, check_in_date, check_out_date, tableIsAvailable) {
    }*/
}

