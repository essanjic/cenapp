package com.group6.cenapp.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group6.cenapp.model.entity.Category;
import com.group6.cenapp.model.entity.Restaurant;
import com.group6.cenapp.repository.CategoryRepository;
import com.group6.cenapp.response.ApiResponseHandler;
import com.group6.cenapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/v1/api/restaurants")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Restaurant>> listRestaurant() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findRestaurant(@PathVariable Integer id)  {
        Optional<Restaurant> productoBuscado = restaurantService.getRestaurantById(id);
        if(productoBuscado.isPresent())
            return ApiResponseHandler.generateResponse("Restaurant data retrieved successfully", HttpStatus.OK, productoBuscado.get());

        return ApiResponseHandler.generateResponseError("Restaurant "+ id + " not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Restaurant>> searchRestaurantByCategory(@PathVariable Category category) {
        List<Restaurant> restaurantSearches = restaurantService.getRestaurantByCategory(category);
         if(!restaurantSearches.isEmpty()){
            return ResponseEntity.ok(restaurantSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        if (restaurant.getCategory() == null || restaurant.getCategory().getCategory() == null) {
            // Manejar el caso en que la categoría no está presente en el cuerpo de la solicitud
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Integer categoryId = restaurant.getCategory().getCategory();

        // Obtener la categoría existente por su ID
        Category existingCategory = categoryRepository.findById(categoryId).orElse(null);

        if (existingCategory == null) {
            // Manejar el caso donde la categoría no se encuentra
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Asociar la categoría existente al restaurante
        restaurant.setCategory(existingCategory);

        // Guardar el restaurante
        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);

        return ResponseEntity.ok(savedRestaurant);
    }


    @PutMapping("/update")
    public ResponseEntity<?> editRestaurant(@RequestBody Restaurant restaurant) throws Exception{
        Optional<Restaurant> productoBuscado = restaurantService.getRestaurantById(restaurant.getId_restaurant());
        if(productoBuscado.isPresent()){
            return ResponseEntity.ok(restaurantService.updateRestaurant(restaurant));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El restaurante con ID: " + restaurant.getId_restaurant() + " no se encuentra ");
        }

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id){
        if(restaurantService.getRestaurantById(id).isPresent()){
            restaurantService.deleteRestaurantById(id);
            return ResponseEntity.ok("Se eliminó con éxito el restaurante con ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el restaurante con ID: " + id);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Restaurant>> searchRestaurantByCategory(@PathVariable Integer id) {
        List<Restaurant> restaurantSearches = restaurantService.getRestaurantByCity(id);
        if(!restaurantSearches.isEmpty()){
            return ResponseEntity.ok(restaurantSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/dates/{startDate}/{endDate}")
    public ResponseEntity<List<Restaurant>> searchrestaurantByRangeDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<Restaurant> restaurantSearches = restaurantService.getRestaurantByRangeDate(startDate, endDate);
        if(!restaurantSearches.isEmpty()){
            return ResponseEntity.ok(restaurantSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/cityAndDates/{cityId}/{startDate}/{endDate}")
    public ResponseEntity<List<Restaurant>> searchrestaurantByRangeDate(@PathVariable Integer cityId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<Restaurant> restaurantSearches = restaurantService.getRestaurantByCityAndRangeDate(cityId, startDate, endDate);
        if(!restaurantSearches.isEmpty()){
            return ResponseEntity.ok(restaurantSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("findAll/random")
    public ResponseEntity<List<Restaurant>> findAllRandom(){
        return ResponseEntity.ok(restaurantService.getRandomRestaurant());
    }
}



