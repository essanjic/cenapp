package com.group6.cenapp.controller;

import com.group6.cenapp.model.entity.City;
import com.group6.cenapp.model.entity.Country;
import com.group6.cenapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> findAll() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<City> findCity(@PathVariable Integer id) {
        Optional<City> ciudadBuscada = cityService.getCityById(id);
        if (ciudadBuscada.isPresent()) {
            return ResponseEntity.ok(ciudadBuscada.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> findAllCountries() {
        return ResponseEntity.ok(cityService.getAllCountries());
    }

    @GetMapping("/countries/{id_country}/cities")
    public ResponseEntity<List<City>> findAllByCountryId(@PathVariable Integer id_country) {
        return ResponseEntity.ok(cityService.findAllByCountryId(id_country));
    }

}