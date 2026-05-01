package com.hotel.hotel.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hotel.hotel.model.reservas;
import com.hotel.hotel.service.reservasServiceImpl;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(reservasController.class)
class reservasControllerTest {

    @Autowired 
    private MockMvc mockMvc;
    @MockitoBean
    private reservasServiceImpl reservasService;
    @Autowired
    private ObjectMapper objectMapper;
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
    void testGetReservaById() throws Exception {
        when(reservasService.getReservaById(1L)).thenReturn(reserva);
        mockMvc.perform(get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Habitacion 101"))
                .andExpect(jsonPath("$.tipo").value("Doble"))
                .andExpect(jsonPath("$.precio").value(10000))
                .andExpect(jsonPath("$.disponible").value(true));
    }

    @Test
    void testCreateReserva() throws Exception {
        when(reservasService.createReserva(any(reservas.class))).thenReturn(reserva);
        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Habitacion 101"))
                .andExpect(jsonPath("$.tipo").value("Doble"))
                .andExpect(jsonPath("$.precio").value(10000))
                .andExpect(jsonPath("$.disponible").value(true));
    }

    @Test
    void testDeleteReserva() throws Exception {
        doNothing().when(reservasService).deleteReserva(1L);
        mockMvc.perform(delete("/reservas/1"))
                .andExpect(status().isOk());
        verify(reservasService, times(1)).deleteReserva(1L);
    }











}
