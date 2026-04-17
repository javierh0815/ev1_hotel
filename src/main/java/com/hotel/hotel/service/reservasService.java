package com.hotel.hotel.service;

import com.hotel.hotel.model.reservas;
import java.util.List;

public interface reservasService {

    List<reservas> getAllReservas();
    reservas getReservaById(Long id);  
    reservas createReserva(reservas reserva);
    reservas updateReserva(Long id, reservas reserva);
    void deleteReserva(Long id);
}