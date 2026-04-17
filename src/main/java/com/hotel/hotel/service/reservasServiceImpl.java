package com.hotel.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.hotel.hotel.model.reservas;
import com.hotel.hotel.repository.reservaRepository;

@Service
public class reservasServiceImpl implements reservasService {

    @Autowired
    private reservaRepository reservaRepository;

    @Override
    public List<reservas> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Optional<reservas> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public reservas createReserva(reservas reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public reservas updateReserva(Long id, reservas reserva) {
        return reservaRepository.findById(id).map(existingReserva -> {
            existingReserva.setTipo(reserva.getTipo());
            existingReserva.setPrecio(reserva.getPrecio());
            existingReserva.setDisponible(reserva.isDisponible());
            return reservaRepository.save(existingReserva);
        }).orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    @Override
    public void deleteReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar: Reserva no encontrada con ID: " + id);
        }
    }
}