package com.hotel.hotel.service;


import com.hotel.hotel.model.reservas;
import java.util.List;
import java.util.Optional;

public interface reservasService {

    List<reservas> getAllReservas();
    Optional<reservas> getReservaById(Long id);
    reservas createReserva(reservas reserva);
    reservas updateReserva(Long id, reservas reserva);
    void deleteReserva(Long id);
}