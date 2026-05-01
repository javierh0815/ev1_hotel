package com.hotel.hotel.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class reservasTest {
    @Test
    void testReservaCreation() {

        reservas reserva = new reservas();
        reserva.setId(1L);
        reserva.setNombre("Habitacion 101");
        reserva.setTipo("Doble");
        reserva.setPrecio(10000);
        reserva.setDisponible(true);
        assertEquals(1L, reserva.getId());
        assertEquals("Habitacion 101", reserva.getNombre());
        assertEquals("Doble", reserva.getTipo());
        assertEquals(10000, reserva.getPrecio());
        assertEquals(true, reserva.isDisponible());
    }

    








}
