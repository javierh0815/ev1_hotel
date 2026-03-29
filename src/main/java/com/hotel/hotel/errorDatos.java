package com.hotel.hotel;



public class errorDatos {
    //para manejar validacion de datos
    private int id;
    private String mensajeError;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMensajeError() {
        return mensajeError;
    }
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public errorDatos(int id, String mensajeError) {
        this.id = id;
        this.mensajeError = mensajeError;
    }
















}
