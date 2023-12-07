package com.group6.cenapp.repository;

import com.group6.cenapp.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("SELECT c FROM City c WHERE c.id_country.id_country = ?1")
    List<City> findAllByCountryId(Integer id_country);
}
