package com.example.managentorapp.entitities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Propiedad implements Serializable {

    @SerializedName("ID_PROPIEDAD")
    private int id_propiedad;
    @SerializedName("DIRECCION")
    private String direccion;

    @SerializedName("DEPOSITO")
    private int precio;

    @SerializedName("CIUDAD")
    private String ciudad;

    @SerializedName("NUM_HAB")
    private int numHab;

    @SerializedName("NUM_BANIO")
    private int numBanio;
    @SerializedName("ASCENSOR")
    private boolean ascensor;
    @SerializedName("METROS")
    private  int metros;
    @SerializedName("IMAGEN")
    private  String imagen;

    @SerializedName("COMENTARIOS")
    private String comentariosAdicionales;
    @SerializedName("FURNISHED")
    private String furnished;
    @SerializedName("PROPIETARIO")
    private String propietario;
    @SerializedName("ESTADO")
    private String estado;
    @SerializedName("ZIPCODE")
    private int zipcode;


    public Propiedad() {
    }


    public Propiedad(int id_propiedad, String direccion, int precio, String ciudad, int numHab, int numBanio, boolean ascensor, int metros, String imagen, String comentariosAdicionales, String furnished, String propietario, String estado, int zipcode) {
        this.id_propiedad = id_propiedad;
        this.direccion = direccion;
        this.precio = precio;
        this.ciudad = ciudad;
        this.numHab = numHab;
        this.numBanio = numBanio;
        this.ascensor = ascensor;
        this.metros = metros;
        this.imagen = imagen;
        this.comentariosAdicionales = comentariosAdicionales;
        this.furnished = furnished;
        this.propietario = propietario;
        this.estado = estado;
        this.zipcode = zipcode;
    }


    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getId_propiedad() {
        return id_propiedad;
    }

    public void setId_propiedad(int id_propiedad) {
        this.id_propiedad = id_propiedad;
    }

    public String getComentariosAdicionales() {
        return comentariosAdicionales;
    }

    public void setComentariosAdicionales(String comentariosAdicionales) {
        this.comentariosAdicionales = comentariosAdicionales;
    }

    public String getFurnished() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNumHab() {
        return numHab;
    }

    public void setNumHab(int numHab) {
        this.numHab = numHab;
    }

    public int getNumBanio() {
        return numBanio;
    }

    public void setNumBanio(int numBanio) {
        this.numBanio = numBanio;
    }

    public boolean isAscensor() {
        return ascensor;
    }

    public void setAscensor(boolean ascensor) {
        this.ascensor = ascensor;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
