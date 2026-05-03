package com.hotel.hotel.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

import com.hotel.hotel.model.reservas;
import com.hotel.hotel.service.reservasService;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

@RestController
@RequestMapping("/reservas")
public class reservasController {
    
    @Autowired
    private reservasService reservasService;

    @GetMapping
    public CollectionModel<EntityModel<reservas>> getAllReservas() {
        List<reservas> lista = reservasService.getAllReservas();
        List<EntityModel<reservas>> reservasModels = lista.stream()
                .map(reserva -> EntityModel.of(reserva, linkTo(methodOn(reservasController.class).getReservaById(reserva.getId())).withSelfRel()))
                .collect(Collectors.toList());
        return CollectionModel.of(reservasModels, linkTo(methodOn(reservasController.class).getAllReservas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<reservas>> getReservaById(@PathVariable Long id) {
        reservas reserva = reservasService.getReservaById(id);

        EntityModel<reservas> model = EntityModel.of(reserva,
            linkTo(methodOn(reservasController.class).getReservaById(id)).withSelfRel(),
            linkTo(methodOn(reservasController.class).getAllReservas()).withRel("reservas"));

        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<EntityModel<reservas>> createReserva(@Valid @RequestBody reservas reserva) {
        reservas nuevaReserva = reservasService.createReserva(reserva);

        EntityModel<reservas> model = EntityModel.of(nuevaReserva,
                linkTo(methodOn(reservasController.class).getReservaById(nuevaReserva.getId())).withSelfRel(),
                linkTo(methodOn(reservasController.class).getAllReservas()).withRel("reservas"));

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<reservas>> updateReserva(@PathVariable Long id, @Valid @RequestBody reservas reserva) {
        reservas reservaActualizada = reservasService.updateReserva(id, reserva);

        EntityModel<reservas> model = EntityModel.of(reservaActualizada,
                linkTo(methodOn(reservasController.class).getReservaById(id)).withSelfRel(),
                linkTo(methodOn(reservasController.class).getAllReservas()).withRel("reservas"));

        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservasService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}