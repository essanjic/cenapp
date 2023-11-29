package com.group6.cenapp.repository;

import com.group6.cenapp.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    /*@Query(value="SELECT c * FROM city c WHERE country_id = ?1", nativeQuery = true)
    List<City> findAllByCountryId(Integer countryId);*/
}
