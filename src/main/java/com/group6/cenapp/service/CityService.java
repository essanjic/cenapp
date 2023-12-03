package com.group6.cenapp.service;
import com.group6.cenapp.model.entity.City;
import com.group6.cenapp.model.entity.Country;
import com.group6.cenapp.repository.CityRepository;
import com.group6.cenapp.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;


    public List<City> getAllCities() {
        return cityRepository.findAll();
    }


    public Optional<City> getCityById(Integer id) {
        return cityRepository.findById(id);
    }

    public List<City> findAllByCountryId(Integer id_country) {
        return cityRepository.findAllByCountryId(id_country);
    }
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
