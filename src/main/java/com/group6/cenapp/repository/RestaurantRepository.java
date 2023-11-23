package com.group6.cenapp.repository;

import com.group6.cenapp.model.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query(value = "select P.* from restaurant P where P.id not in (select distinct R.restaurant from reservation R where (R.checkout_date >= ?1 and R.check_in_date <= ?2));", nativeQuery = true)
    List<Restaurant> getByRangeDate(LocalDate check_in_date, LocalDate check_out_date);

    @Query(value = "select P.* from restaurant P where P.city_id = ?1 and P.id not in (select distinct R.restaurant from reservation R where (R.checkout_date >= ?2 and R.check_in_date <= ?3));", nativeQuery = true)
    List<Restaurant> getByCityAndRangeDate(Integer city_id, LocalDate check_in_date, LocalDate check_out_date);
    @Query(value = "SELECT * FROM restaurant ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Restaurant> getRandomRestaurant();
}
