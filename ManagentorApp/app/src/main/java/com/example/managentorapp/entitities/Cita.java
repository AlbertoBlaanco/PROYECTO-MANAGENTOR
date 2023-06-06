package com.example.managentorapp.entitities;

public class Cita {
    private int idCita;
    private int idInmueble;
    private int idCliente;
    private String hora;
    private String lugar;
    private String motivo;
    private String dia;


    public Cita() {

    }

    public Cita(int idCita, int idInmueble, int idCliente, String hora, String lugar, String motivo, String dia) {
        this.idCita = idCita;
        this.idInmueble = idInmueble;
        this.idCliente = idCliente;
        this.hora = hora;
        this.lugar = lugar;
        this.motivo = motivo;
        this.dia = dia;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getidInmueble() {
        return idInmueble;
    }

    public void setidInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
