package com.group6.cenapp.repository;


import com.group6.cenapp.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByRestaurantId(Integer restaurantId);
    List<Reservation> findByUserId(Integer userInfoId);


}
