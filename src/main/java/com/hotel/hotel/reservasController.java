package com.hotel.hotel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class reservasController {

    //lista
    private List<reservas> listaHabitaciones = new ArrayList<>();

    
    public reservasController() {
        //registros
        listaHabitaciones.add(new reservas(1, "Simple", 35000, true));
        listaHabitaciones.add(new reservas(2, "Doble", 55000, true));
        listaHabitaciones.add(new reservas(3, "Suite", 90000, false));
        listaHabitaciones.add(new reservas(4, "Simple", 35000, true));
        listaHabitaciones.add(new reservas(5, "Doble", 55000, false));


    }

    //consultar disponibilidad por id de habitacion
    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarReservas(@PathVariable int id) {
        for (reservas c : listaHabitaciones) {
            if(c.getId() == id) {
                return ResponseEntity.ok(c);
            }
        }
        errorDatos error = new errorDatos(id, "La habitación no existe en el sistema");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //consultar disponibilidad por estado, muestra todo que tenga disponible true
    @GetMapping("/disponibles")
    public List<reservas> verDisponibles() {
        List<reservas> dispOK = new ArrayList<>();
        for (reservas d : listaHabitaciones) {
            if(d.isDisponible()){
                dispOK.add(d);
            }
        }
        return dispOK;
    }

    //cancelar una reserva
    @GetMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelarReservas(@PathVariable int id){
        for (reservas can : listaHabitaciones) {
            if (can.getId() == id) {
                can.setDisponible(true);
                return ResponseEntity.ok(can);
            }
        }
        errorDatos error = new errorDatos(id, "No es posible cancelar: no existe la habitación");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //crear una reserva nueva (con checkeo de id de habitacion y estado disponible)
    @GetMapping("/reservar/{id}")
    public ResponseEntity<?> reservaNueva(@PathVariable int id){
        if (id < 1 || id > listaHabitaciones.size()) {
            errorDatos errorRango = new errorDatos(id, "La habitación seleccionada no existe en el sistema");
            return new ResponseEntity<>(errorRango, HttpStatus.BAD_REQUEST);
        }
        reservas habitacionEncontrada = listaHabitaciones.get(id - 1);

        if (!habitacionEncontrada.isDisponible()) {
            errorDatos errorEstHab = new errorDatos(id, "La habitación ya está ocupada");
            return new ResponseEntity<>(errorEstHab, HttpStatus.CONFLICT);
        }

        habitacionEncontrada.setDisponible(false);
        return ResponseEntity.ok(habitacionEncontrada);

    }
    










}
    

