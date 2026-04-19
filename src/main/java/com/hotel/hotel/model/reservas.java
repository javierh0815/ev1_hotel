package com.hotel.hotel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "reservas")
public class reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Error de formato: debe ingresar el nombre o número de la habitación")
    @Size(min = 2, max = 50, message = "Error de formato: El nombre debe tener entre 2 y 50 caracteres")
    @Column(name = "nombre", unique = true, nullable = false   )
    private String nombre;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "Error de formato: Caracteres inválidos detectados")
    @Size(min = 2, max = 50, message = "Error de formato: Nombre del tipo de habitación debe tener entre 2 y 50 caracteres")
    @NotBlank(message = "Error de formato: debe ingresar un nombre de tipo de habitación")
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Min(value = 1, message = "Error de formato: El precio debe ser mayor a 0")
    @NotNull(message = "Error: El precio es obligatorio y no puede estar vacío")
    @Column(name = "precio", nullable = false)
    private Integer precio;

    @NotNull(message = "Error de formato: Debe especificar si la habitación está disponible o no")
    @Column(name = "disponible", nullable = false)
    private boolean disponible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public reservas() {
    }

    public reservas(Long id, String nombre, String tipo, Integer precio, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
    }
}