package com.group6.cenapp.service;


import com.group6.cenapp.exception.ResourceNotFoundException;
import com.group6.cenapp.model.entity.Reservation;
import com.group6.cenapp.model.entity.UserInfo;
import com.group6.cenapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserInfoService userInfoService;


    public List<Reservation> findByRestaurantId(Integer restaurantId) {
        return reservationRepository.findByRestaurantId(restaurantId);
    }

    public List<Reservation> findByUser_id(Integer userId) {
        return reservationRepository.findByUserId(userId);
    }

public UserInfo findByEmail(String email) throws ResourceNotFoundException {
        return userInfoService.findByEmail(email);
    }


    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }


    public Optional<Reservation> getReservationById(Integer id) {
        return reservationRepository.findById(id);
    }


    public Reservation saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
        return reservation;
    }


    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void deleteReservationById(Integer id) {
        reservationRepository.deleteById(id);
    }

}
