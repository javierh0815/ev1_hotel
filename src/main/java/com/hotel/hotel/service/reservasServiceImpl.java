package com.hotel.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.hotel.hotel.model.reservas;
import com.hotel.hotel.repository.reservaRepository;
import com.hotel.hotel.exception.ReservaNotFoundException;

@Service
public class reservasServiceImpl implements reservasService {

    @Autowired
    private reservaRepository reservaRepository;

    @Override
    public List<reservas> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public reservas getReservaById(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));
    }

    @Override
    public reservas createReserva(reservas reserva) {
        if (reservaRepository.existsByNombre(reserva.getNombre())) {
            throw new RuntimeException("Ya existe una habitación con el nombre: " + reserva.getNombre());
        }
            return reservaRepository.save(reserva);
    }

    @Override
        public reservas updateReserva(Long id, reservas reserva) {
            return reservaRepository.findById(id).map(existingReserva -> {
                if (!existingReserva.getNombre().equals(reserva.getNombre()) && 
                    reservaRepository.existsByNombre(reserva.getNombre())) {
                    throw new RuntimeException("El nombre '" + reserva.getNombre() + "' ya está ocupado por otra habitación.");
                }
                
                existingReserva.setNombre(reserva.getNombre());
                existingReserva.setTipo(reserva.getTipo());
                existingReserva.setPrecio(reserva.getPrecio());
                existingReserva.setDisponible(reserva.isDisponible());
                return reservaRepository.save(existingReserva);
            }).orElseThrow(() -> new ReservaNotFoundException(id));
    }

    @Override
    public void deleteReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new ReservaNotFoundException(id);
        }
        reservaRepository.deleteById(id);
    }
}