package com.hotel.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.hotel.model.reservas;


public interface reservaRepository extends JpaRepository<reservas, Long> {
    boolean existsByNombre(String nombre);
    
}
