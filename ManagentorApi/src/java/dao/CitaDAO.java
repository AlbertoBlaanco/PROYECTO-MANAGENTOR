/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Cita;
import interfacesDAO.SQLTools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import motor.MotorMySQL;

/**
 *
 * @author S2-PC00
 */
public class CitaDAO {

    private SQLTools miMotor = null;

    private static final String SELECT_ALL = "SELECT * FROM cita WHERE 1=1";

    private static final String SQL_ADD = "INSERT INTO Cita (idInmueble, idCliente, Hora, Lugar, MotivoCita,Dia)"
            + "VALUES ('";

    public CitaDAO() {
        this.miMotor = new MotorMySQL();
    }

    public int addCita(Cita bean) {

        String sql_filtro = "";
        String sql_final = "";
        int filasAF = 0;
        try {
            this.miMotor.connect();

            if (bean != null) {

                sql_filtro += +bean.getidInmueble() + "', '"
                        + bean.getIdCliente() + "', '"
                        + (bean.getHora() != null ? bean.getHora() : "") + "', '"
                        + (bean.getLugar() != null ? bean.getLugar() : "") + "', '"
                        + (bean.getMotivo() != null ? bean.getMotivo() : "") + "', '"
                        + (bean.getDia() != null ? bean.getDia() : "") + "')";

            }

            sql_final = SQL_ADD + sql_filtro;
            System.out.println(sql_final);

            filasAF = miMotor.execute(sql_final);

            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(PropiedadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasAF;
    }

    public ArrayList<Cita> findAll(Cita bean) {
        String sql_filtro = "";
        String sql_final = "";
        ArrayList<Cita> lstCitas = null;
        try {
            this.miMotor.connect();

            if (bean != null) {
                if (bean.getIdCita() != 0) {
                    sql_filtro += "AND idCita='" + bean.getIdCita() + "'";
                }
                if (bean.getidInmueble() != 0) {
                    sql_filtro += "AND idInmueble='" + bean.getidInmueble() + "'";
                }
                if (bean.getIdCliente() != 0) {
                    sql_filtro += "AND idCliente='" + bean.getIdCliente() + "'";
                }
                if (bean.getHora().length() > 0 && !bean.getHora().equals("") && bean.getHora() != null) {
                    sql_filtro += "AND Hora='" + bean.getHora() + "'";
                }

                if (bean.getLugar().length() > 0 && !bean.getLugar().equals("") && bean.getLugar() != null) {
                    sql_filtro += "AND Lugar='" + bean.getLugar() + "'";
                }

                if (bean.getMotivo().length() > 0 && !bean.getMotivo().equals("") && bean.getMotivo() != null) {
                    sql_filtro += "AND MotivoCita='" + bean.getMotivo() + "'";
                }

                if (bean.getDia().length() > 0 && !bean.getDia().equals("") && bean.getDia() != null) {
                    sql_filtro += "AND Dia='" + bean.getDia() + "'";
                }

            }

            sql_final = SELECT_ALL + sql_filtro;

            ResultSet resultset = this.miMotor.executeQuery(sql_final);

            if (resultset != null) {
                lstCitas = new ArrayList();

                while (resultset.next()) {
                    Cita entidad = new Cita();
                    entidad.setIdCita(resultset.getInt(1));
                    entidad.setidInmueble(resultset.getInt(2));
                    entidad.setIdCliente(resultset.getInt(3));
                    entidad.setHora(resultset.getString(4));
                    entidad.setLugar(resultset.getString(5));
                    entidad.setMotivo(resultset.getString(6));
                    entidad.setDia(resultset.getString(7));

                    lstCitas.add(entidad);
                }
            }
            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(PropiedadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstCitas;
    }
}
