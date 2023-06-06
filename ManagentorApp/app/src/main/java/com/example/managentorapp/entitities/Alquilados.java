package com.example.managentorapp.entitities;

import com.google.gson.annotations.SerializedName;

public class Alquilados {
    @SerializedName("ID_ALQUILADOS")
    private int id_alquilados;
    @SerializedName("ID_CLIENTE")
    private int id_inmueble;
    @SerializedName("ID_INMUEBLE")
    private int id_cliente;
    @SerializedName("FECHA")
    private String fecha;

    public Alquilados() {
    }

    public Alquilados(int id_alquilados, int id_inmueble, int id_cliente, String fecha) {
        this.id_alquilados = id_alquilados;
        this.id_inmueble = id_inmueble;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
    }

    public int getId_alquilados() {
        return id_alquilados;
    }

    public void setId_alquilados(int id_alquilados) {
        this.id_alquilados = id_alquilados;
    }

    public int getId_inmueble() {
        return id_inmueble;
    }

    public void setId_inmueble(int id_inmueble) {
        this.id_inmueble = id_inmueble;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
