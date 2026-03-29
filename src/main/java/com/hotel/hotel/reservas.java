package com.hotel.hotel;

public class reservas {

    private int id;
    private String tipo;
    private int precio;
    private boolean disponible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public reservas(int id, String tipo, int precio, boolean disponible) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
    }
    
}
