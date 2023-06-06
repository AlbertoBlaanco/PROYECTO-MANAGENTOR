package com.example.managentorapp.entitities;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("ID_EMPLE")
    private int idEmple;
    @SerializedName("DIRECCION")
    private String Direccion;
    @SerializedName("FECHA_NAC")
    private String Fecha_Nac;
    @SerializedName("DNI")
    private String DNI;
    @SerializedName("NOMBRE")
    private String Nombre;
    @SerializedName("APELLIDO")
    private String Apellido;
    @SerializedName("PASSWORD")
    private String Password;
    @SerializedName("EMPRESA")
    private String Empresa;

    @SerializedName("TELEFONO")
    private String Telefono;
    @SerializedName("CIUDAD")
    private String Ciudad;
    @SerializedName("EMAIL")
    private String email;

    public Usuario() {

    }

    public Usuario(int idEmple,String direccion, String fecha_Nac, String DNI, String nombre, String apellido,String password, String empresa, String telefono, String ciudad,String email) {
        this.idEmple = idEmple;
        this.Direccion = direccion;
        this.Fecha_Nac = fecha_Nac;
        this.DNI = DNI;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Empresa = empresa;
        this.Telefono = telefono;
        this.Ciudad = ciudad;
        this.Password = password;
        this.email = email;
    }

    public int getIdEmple() {
        return idEmple;
    }

    public void setIdEmple(int idEmple) {
        this.idEmple = idEmple;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getFecha_Nac() {
        return Fecha_Nac;
    }

    public void setFecha_Nac(String fecha_Nac) {
        Fecha_Nac = fecha_Nac;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
