/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Usuario;
import interfacesDAO.SQLTools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import motor.MotorMySQL;

/**
 *
 * @author alber
 */
public class UsuarioDAO {

    private SQLTools miMotor = null;
    private static final String SELECT_ONE_USER = "SELECT * FROM empleado WHERE 1=1 ";
    private static final String SQL_ADD = "INSERT INTO empleado (Direccion,Fecha_Nac, DNI, Nombre, Apellido, Password, Empresa, Telefono, Ciudad, Email) "
            + "VALUES ('";

    public UsuarioDAO() {
        this.miMotor = new MotorMySQL();
    }

    public Usuario findOne(Usuario usuario) {

        String sql_filtro = "";
        String sql_final = "";
        Usuario entidad = new Usuario();
        
         if (usuario.getIdEmple() != 0) {
            sql_filtro += "AND idEmple='" + usuario.getIdEmple() + "'";
        }

        if (usuario.getEmail() != null) {
            sql_filtro += "AND Email='" + usuario.getEmail() + "'";
        }

        sql_final = SELECT_ONE_USER + sql_filtro;

        try {
            //1º) 
            miMotor.connect();

            ResultSet resultset = miMotor.
                    executeQuery(sql_final);
            if (resultset != null) {
                while (resultset.next()) {
                    entidad.setIdEmple(resultset.getInt(1));
                    entidad.setDireccion(resultset.getString(2));
                    entidad.setFecha_Nac(resultset.getString(3));
                    entidad.setDNI(resultset.getString(4));
                    entidad.setNombre(resultset.getString(5));
                    entidad.setApellido(resultset.getString(6));
                    entidad.setPassword(resultset.getString(7));
                    entidad.setEmpresa(resultset.getString(8));
                    entidad.setTelefono(resultset.getString(9));
                    entidad.setCiudad(resultset.getString(10));
                    entidad.setEmail(resultset.getString(11));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            miMotor.disconnect();
        }
        return entidad;
    }

    public int addUsuario(Usuario bean) {

        String sql_filtro = "";
        String sql_final = "";
        int filasAF = 0;
        try {
            this.miMotor.connect();

            if (bean != null) {

                sql_filtro += (bean.getDireccion() != null ? bean.getDireccion() : "") + "', "
                        + (bean.getFecha_Nac() != null ? bean.getFecha_Nac() : "") + "', "
                        + (bean.getDNI() != null ? bean.getDNI() : "") + "', "
                        + (bean.getNombre() != null ? bean.getNombre() : "") + "', "
                        + (bean.getApellido() != null ? bean.getApellido() : "") + "', "
                        + (bean.getPassword() != null ? bean.getPassword() : "") + "', "
                        + (bean.getEmpresa() != null ? bean.getEmpresa() : "") + "', '"
                        + (bean.getTelefono() != null ? bean.getTelefono() : "") + "', "
                        + (bean.getCiudad() != null ? bean.getCiudad() : "") + "', '"
                        + (bean.getEmail() != null ? bean.getEmail() : "") + "')";

            }

            sql_final = SQL_ADD + sql_filtro;

            filasAF = miMotor.execute(sql_final);

            //ResultSet resultset = this.miMotor.executeQuery(sql_final);
            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(PropiedadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasAF;
    }

}
