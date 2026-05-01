package com.hotel.hotel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.hotel.model.reservas;
import com.hotel.hotel.repository.reservaRepository;

@ExtendWith(MockitoExtension.class)
class reservasServiceImplTest {

    @Mock
    private reservaRepository reservaRepository;
    @InjectMocks
    private reservasServiceImpl reservasService;

    private reservas reserva;

    @BeforeEach
    void setUp() {
        reserva = new reservas();
        reserva.setId(1L);
        reserva.setNombre("Habitacion 101");
        reserva.setTipo("Doble");
        reserva.setPrecio(10000);
        reserva.setDisponible(true);
    }

    @Test
    void testgetReservaById() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        assertEquals(Optional.of(reserva), reservasService.getReservaById(1L));
    }

    @Test
    void testCreateReserva() {
        when(reservaRepository.existsByNombre(reserva.getNombre())).thenReturn(false);
        when(reservaRepository.save(reserva)).thenReturn(reserva);
        assertEquals(reserva, reservasService.createReserva(reserva));
    }

    @Test
    void testUpdateReservaExists() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaRepository.existsByNombre("Habitacion 102")).thenReturn(false);
        reserva.setNombre("Habitacion 102");
        when(reservaRepository.save(reserva)).thenReturn(reserva);
        assertEquals(reserva, reservasService.updateReserva(1L, reserva));
    }

    @Test
    void testUpdateReservaNotExists() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            reservasService.updateReserva(1L, reserva);
        });
        assertEquals("Reserva no encontrada con ID: 1", exception.getMessage());
    }


    @Test
    void testDeleteReserva() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        reservasService.deleteReserva(1L);
        // Verificar que la reserva fue eliminada
    }

}
