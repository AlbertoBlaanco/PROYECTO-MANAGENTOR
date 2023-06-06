/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Imagenes;
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
public class ImagenesDAO {

    private SQLTools miMotor = null;

    private static final String SELECT_ALL = "SELECT * FROM Imagenes WHERE 1=1 ";

    private static final String SQL_ADD = "INSERT INTO imagenes (Id_Inmueble, Url)"
            + "VALUES ('";

    public ImagenesDAO() {
        this.miMotor = new MotorMySQL();
    }

    public int addImagen(Imagenes bean) {

        String sql_filtro = "";
        String sql_final = "";
        int filasAF = 0;
        try {
            this.miMotor.connect();

            if (bean != null) {

                sql_filtro += +bean.getID_INMUEBLE() + "', '"
                        + (bean.getURL() != null ? bean.getURL() : "") + "')";

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

    public ArrayList<Imagenes> findAll(Imagenes bean) {
        String sql_filtro = "";
        String sql_final = "";
        ArrayList<Imagenes> lstImagenes = null;
        try {
            this.miMotor.connect();

            if (bean != null) {
                if (bean.getID_IMAGEN() != 0) {
                    sql_filtro += "AND id_Imagen='" + bean.getID_IMAGEN() + "'";
                }
                if (bean.getID_INMUEBLE() != 0) {
                    sql_filtro += "AND Id_Inmueble='" + bean.getID_INMUEBLE() + "'";
                }

                if (bean.getURL() != null) {
                    sql_filtro += "AND URL='" + bean.getURL() + "'";
                }

            }

            sql_final = SELECT_ALL + sql_filtro;

            ResultSet resultset = this.miMotor.executeQuery(sql_final);

            if (resultset != null) {
                lstImagenes = new ArrayList();

                while (resultset.next()) {
                    Imagenes entidad = new Imagenes();
                    entidad.setID_IMAGEN(resultset.getInt(1));
                    entidad.setID_INMUEBLE(resultset.getInt(2));
                    entidad.setURL(resultset.getString(3));

                    lstImagenes.add(entidad);
                }
            }
            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(PropiedadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstImagenes;
    }
}
