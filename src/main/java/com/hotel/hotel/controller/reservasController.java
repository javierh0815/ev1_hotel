package com.hotel.hotel.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.hotel.hotel.model.reservas;
import com.hotel.hotel.service.reservasService;

@RestController
@RequestMapping("/reservas")
public class reservasController {
    
    @Autowired
    private reservasService reservasService;

    @GetMapping
    public List<reservas> getAllReservas() {
        return reservasService.getAllReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<reservas> getReservaById(@PathVariable Long id) {
        return ResponseEntity.ok(reservasService.getReservaById(id));
    }

    @PostMapping
    public ResponseEntity<reservas> createReserva(@Valid @RequestBody reservas reserva) {
        return new ResponseEntity<>(reservasService.createReserva(reserva), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<reservas> updateReserva(@PathVariable Long id, @Valid @RequestBody reservas reserva) {
        return ResponseEntity.ok(reservasService.updateReserva(id, reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReserva(@PathVariable Long id) {
        reservasService.deleteReserva(id);
        return ResponseEntity.ok("Reserva eliminada con éxito (ID: " + id + ")");
    }
}