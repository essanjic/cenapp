package com.group6.cenapp.repository;

import com.group6.cenapp.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query(value="SELECT c FROM city c WHERE id_country =?1", nativeQuery = true)
    List<City> findAllByCountryId(Integer id_country);
}
