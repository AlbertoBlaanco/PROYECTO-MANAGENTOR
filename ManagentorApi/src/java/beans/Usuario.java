/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class Usuario {

    private int ID_EMPLE;
    private String DIRECCION;
    private String FECHA_NAC;
    private String DNI;
    private String NOMBRE;
    private String APELLIDO;
    private String PASSWORD;
    private String EMPRESA;
    private String TELEFONO;
    private String CIUDAD;
    private String EMAIL;

    public Usuario() {

    }

    public Usuario(int idEmple, String direccion, String fecha_Nac, String DNI, String nombre, String apellido, String password, String empresa, String telefono, String ciudad, String email) {
        this.ID_EMPLE = idEmple;
        this.DIRECCION = direccion;
        this.FECHA_NAC = fecha_Nac;
        this.DNI = DNI;
        this.NOMBRE = nombre;
        this.APELLIDO = apellido;
        this.EMPRESA = empresa;
        this.TELEFONO = telefono;
        this.CIUDAD = ciudad;
        this.PASSWORD = password;
        this.EMAIL = email;
    }

    public int getIdEmple() {
        return ID_EMPLE;
    }

    public void setIdEmple(int idEmple) {
        this.ID_EMPLE = idEmple;
    }

    public String getDireccion() {
        return DIRECCION;
    }

    public void setDireccion(String direccion) {
        this.DIRECCION = direccion;
    }

    public String getFecha_Nac() {
        return FECHA_NAC;
    }

    public void setFecha_Nac(String fecha_Nac) {
        this.FECHA_NAC = fecha_Nac;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return NOMBRE;
    }

    public void setNombre(String nombre) {
        this.NOMBRE = nombre;
    }

    public String getApellido() {
        return APELLIDO;
    }

    public void setApellido(String apellido) {
        this.APELLIDO = apellido;
    }

    public String getEmpresa() {
        return EMPRESA;
    }

    public void setEmpresa(String empresa) {
        this.EMPRESA = empresa;
    }

    public String getTelefono() {
        return TELEFONO;
    }

    public void setTelefono(String telefono) {
        this.TELEFONO = telefono;
    }

    public String getCiudad() {
        return CIUDAD;
    }

    public void setCiudad(String ciudad) {
        this.CIUDAD = ciudad;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public void setPassword(String password) {
        this.PASSWORD = password;
    }

    public String getEmail() {
        return EMAIL;
    }

    public void setEmail(String email) {
        this.EMAIL = email;
    }

    public static String fromArrayListToJson(ArrayList<Usuario> lstUsuarios) {
        String resp = "[";
        for (Usuario usuario : lstUsuarios) {
            resp += "{"
                    + "\"ID_EMPLE\" :\"" + usuario.getIdEmple() + "\", "
                    + "\"DIRECCION\" : \"" + usuario.getDireccion() + "\","
                    + "\"FECHA_NAC\" : \"" + usuario.getFecha_Nac() + "\","
                    + "\"DNI\" : \"" + usuario.getDNI() + "\","
                    + "\"NOMBRE\" : \"" + usuario.getNombre() + "\","
                    + "\"APELLIDO\" : \"" + usuario.getApellido() + "\","
                    + "\"PASSWORD\" : \"" + usuario.getPassword() + "\","
                    + "\"EMPRESA\" : \"" + usuario.getEmpresa() + "\","
                    + "\"TELEFONO\" : \"" + usuario.getTelefono() + "\","
                    + "\"CIUDAD\" : \"" + usuario.getCiudad() + "\","
                    + "\"EMAIL\" : \"" + usuario.getEmail() + "\"}";
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String fromArrayListToJsonOne(Usuario usuario) {
        //String resp = "[";
        String resp = "{"
                + "\"ID_EMPLE\" :\"" + usuario.getIdEmple() + "\", "
                + "\"DIRECCION\" : \"" + usuario.getDireccion() + "\","
                + "\"FECHA_NAC\" : \"" + usuario.getFecha_Nac() + "\","
                + "\"DNI\" : \"" + usuario.getDNI() + "\","
                + "\"NOMBRE\" : \"" + usuario.getNombre() + "\","
                + "\"APELLIDO\" : \"" + usuario.getApellido() + "\","
                + "\"PASSWORD\" : \"" + usuario.getPassword() + "\","
                + "\"EMPRESA\" : \"" + usuario.getEmpresa() + "\","
                + "\"TELEFONO\" : \"" + usuario.getTelefono() + "\","
                + "\"CIUDAD\" : \"" + usuario.getCiudad() + "\","
                + "\"EMAIL\" : \"" + usuario.getEmail() + "\"}";
        //resp += "]";

        return resp;

    }
}
