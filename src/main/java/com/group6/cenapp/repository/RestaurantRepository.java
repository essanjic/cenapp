package com.group6.cenapp.repository;

import com.group6.cenapp.model.entity.Category;
import com.group6.cenapp.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> getByCategory(Category category);

    @Query(value = "select R.* from city R where R.city_id = ?1", nativeQuery = true)
    List<Restaurant> getByCity(Integer city_id);

    @Query(value = "select P.* from restaurant P where P.id not in (select distinct R.restaurant from reservation R where (R.checkout_date >= ?1 and R.check_in_date <= ?2));", nativeQuery = true)
    List<Restaurant> getByRangeDate(LocalDate check_in_date, LocalDate check_out_date);

    // Hacer una query donde se traiga los restaurantes por ciudad, rango de fecha
    // categoria y por mesas disponibles

    @Query(value = "select P.* from restaurant P where P.city_id = ?1 and P.id not in (select distinct R.restaurant from reservation R where (R.checkout_date >= ?2 and R.check_in_date <= ?3));", nativeQuery = true)
    List<Restaurant> getByCityAndRangeDate(Integer city_id, LocalDate check_in_date, LocalDate check_out_date);

    @Query(value = "SELECT * FROM restaurant ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Restaurant> getRandomRestaurant();

    // Hacer una query donde la busqueda de restaurante sea por nombre de ciudad,
    // rango de fecha categoria, mesas disponibles, y nombre de restaurante
    @Query(value = "select P.* from restaurant P where P.city_id = ?1 and P.id not in (select distinct R.restaurant from reservation R where (R.checkout_date >= ?2 and R.check_in_date <= ?3)) and P.name like %?4%", nativeQuery = true)
    List<Restaurant> getByCityAndRangeDateAndName(Integer city_id, LocalDate check_in_date, LocalDate check_out_date,
            String name);

}
