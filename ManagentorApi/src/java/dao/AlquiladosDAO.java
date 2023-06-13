/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Alquilados;
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
public class AlquiladosDAO {

    private SQLTools miMotor = null;
    private static final String SELECT_ALL = "SELECT * FROM alquilados WHERE 1=1 ";
    private static final String SQL_ADD = "INSERT INTO alquilados (id_cliente,id_inmueble , Fecha)"
            + "VALUES ('";

    public AlquiladosDAO() {
        this.miMotor = new MotorMySQL();
    }

    public ArrayList<Alquilados> findAll(Alquilados bean) {
        String sql_filtro = "";
        String sql_final = "";
        ArrayList<Alquilados> lstAlquilados = null;
        try {
            this.miMotor.connect();

            if (bean != null) {
                if (bean.getId_alquilados() != 0) {
                    sql_filtro += "AND id_alquilados='" + bean.getId_alquilados() + "'";
                }

                if (bean.getId_cliente() != 0) {
                    sql_filtro += "AND id_cliente='" + bean.getId_cliente() + "'";
                }

                if (bean.getId_inmueble() != 0) {
                    sql_filtro += "AND id_inmueble='" + bean.getId_inmueble() + "'";
                }

                if (bean.getFecha().length() > 0 && !bean.getFecha().equals("") && bean.getFecha() != null) {
                    sql_filtro += "AND Fecha='" + bean.getFecha() + "'";
                }

            }

            sql_final = SELECT_ALL + sql_filtro;

            ResultSet resultset = this.miMotor.executeQuery(sql_final);

            if (resultset != null) {
                lstAlquilados = new ArrayList();

                while (resultset.next()) {
                    Alquilados entidad = new Alquilados();
                    entidad.setId_alquilados(resultset.getInt(1));
                    entidad.setId_inmueble(resultset.getInt(2));
                    entidad.setId_cliente(resultset.getInt(3));
                    entidad.setFecha(resultset.getString(4));
                    lstAlquilados.add(entidad);
                }
            }
            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(AlquiladosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstAlquilados;
    }

    public int addAlquiler(Alquilados bean) {

        String sql_filtro = "";
        String sql_final = "";
        int filasAF = 0;
        try {
            this.miMotor.connect();

            if (bean != null) {

                sql_filtro += +bean.getId_cliente() + "', '"
                        + bean.getId_inmueble()+ "', '"
                        + (bean.getFecha() != null ? bean.getFecha() : "") + "')";

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

    public static void main(String[] args) {
        AlquiladosDAO alquiladosDAO = new AlquiladosDAO();
        //Alquilados alquilados = new Alquilados();
        //alquilados.setId_cliente(1);
        ArrayList<Alquilados> lstClientes = alquiladosDAO.findAll(null);
        System.out.println(Alquilados.fromArrayListToJson(lstClientes));
    }
}
