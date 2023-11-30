package com.group6.cenapp.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group6.cenapp.model.entity.Reservation;
import com.group6.cenapp.model.entity.UserInfo;
import com.group6.cenapp.repository.UserInfoRepository;
import com.group6.cenapp.service.ReservationService;
import com.group6.cenapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/reservations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationController  {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private RestaurantService tableService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> listarResrevas() {
        return ResponseEntity.ok(reservationService.getAllReservation());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Reservation>> findAllByUserId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reservationService.findByUser_id(id), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Reservation>> findAllByRestaurantId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reservationService.findByRestaurantId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> buscarReserva(@PathVariable Integer id) {
        Optional<Reservation> reservaBuscada = reservationService.getReservationById(id);
        if (reservaBuscada.isPresent()) {
            return ResponseEntity.ok(reservaBuscada.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Reservation reservation)  {
        UserInfo user = userInfoRepository.findById(reservation.getUser().getId()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Usuario no se encuentra debe Ingresar o Registrarse  para poder hacer una reserva ");
        } else {
            return ResponseEntity.ok(reservationService.saveReservation(reservation));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> editarReserva (@RequestBody Reservation reservation) {


        Optional<Reservation> reservaBuscada = reservationService.getReservationById(reservation.getId());
        if (reservaBuscada.isPresent()) {
            reservationService.updateReservation(reservation);
            return ResponseEntity.ok("Se Actualizo su  reserva con ID: " + reservation.getId());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Reserva con ID: " + reservation.getId() + " no se encuentra ");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        if(reservationService.getReservationById(id).isPresent()){
            reservationService.deleteReservationById(id);
            return ResponseEntity.ok("Se eliminó con éxito la reserva con ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la reserva con ID: " + id);
    }

}
