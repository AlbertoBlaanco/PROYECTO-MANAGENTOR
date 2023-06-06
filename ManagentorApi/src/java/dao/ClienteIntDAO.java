/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.ClienteInteresado;
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
public class ClienteIntDAO {

    private SQLTools miMotor = null;
    private static final String SELECT_ALL = "SELECT * FROM cliente";
    private static final String SELECT_INICIO = "SELECT * FROM cliente WHERE 1=1 ";
    private static final String SELECT_TIPO_INICIO = "SELECT * FROM cliente WHERE idCliente = ";
    private static final String SQL_ADD = "INSERT INTO cliente (idInmueble, Fecha_nacicli, ApellidoCli, NombreCli, DireccionCli, TelefonoCli)"
            + "VALUES ('";

    public ClienteIntDAO() {
        this.miMotor = new MotorMySQL();
    }

    public ArrayList<ClienteInteresado> findAll(ClienteInteresado bean) {
        String sql_filtro = "";
        String sql_final = "";
        ArrayList<ClienteInteresado> lstClientes = null;
        try {
            this.miMotor.connect();

            if (bean != null) {
                if (bean.getIdCliente() != 0) {
                    sql_filtro += "AND idCliente='" + bean.getIdCliente() + "'";
                }

                if (bean.getIdInmueble() != 0) {
                    sql_filtro += "AND idInmueble='" + bean.getIdInmueble() + "'";
                }

                if (bean.getFecha_nacicli().length() > 0 && !bean.getFecha_nacicli().equals("") && bean.getFecha_nacicli() != null) {
                    sql_filtro += "AND Fecha_nacicli='" + bean.getFecha_nacicli() + "'";
                }

                if (bean.getApellidoCli().length() > 0 && !bean.getApellidoCli().equals("") && bean.getApellidoCli() != null) {
                    sql_filtro += "AND ApellidoCli='" + bean.getApellidoCli() + "'";
                }

                if (bean.getNombreCli().length() > 0 && !bean.getNombreCli().equals("") && bean.getNombreCli() != null) {
                    sql_filtro += "AND NombreCli='" + bean.getNombreCli() + "'";
                }

                if (bean.getDireccionCli().length() > 0 && !bean.getDireccionCli().equals("") && bean.getDireccionCli() != null) {
                    sql_filtro += "AND DireccionCli='" + bean.getDireccionCli() + "'";
                }

                if (bean.getTelefonoCli() != 0) {
                    sql_filtro += "AND TelefonoCli='" + bean.getTelefonoCli() + "'";
                }

            }

            sql_final = SELECT_ALL + sql_filtro;

            ResultSet resultset = this.miMotor.executeQuery(sql_final);

            if (resultset != null) {
                lstClientes = new ArrayList();

                while (resultset.next()) {
                    ClienteInteresado entidad = new ClienteInteresado();
                    entidad.setIdCliente(resultset.getInt(1));
                    entidad.setIdInmueble(resultset.getInt(2));
                    entidad.setFecha_nacicli(resultset.getString(3));
                    entidad.setApellidoCli(resultset.getString(4));
                    entidad.setNombreCli(resultset.getString(5));
                    entidad.setDireccionCli(resultset.getString(6));
                    entidad.setTelefonoCli(resultset.getInt(7));
                    lstClientes.add(entidad);
                }
            }
            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(ClienteIntDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstClientes;
    }

    public ArrayList<ClienteInteresado> findClientesPropiedad(ClienteInteresado bean) {
        String sql_filtro = "";
        String sql_final = "";
        ArrayList<ClienteInteresado> lstClientes = null;
        try {
            this.miMotor.connect();

            if (bean != null) {
                if (bean.getIdCliente() != 0) {
                    sql_filtro += "AND idCliente='" + bean.getIdCliente() + "'";
                }

                if (bean.getIdInmueble() != 0) {
                    sql_filtro += "AND idInmueble='" + bean.getIdInmueble() + "'";
                }

            }

            sql_final = SELECT_INICIO + sql_filtro;

            ResultSet resultset = this.miMotor.executeQuery(sql_final);

            if (resultset != null) {
                lstClientes = new ArrayList();

                while (resultset.next()) {
                    ClienteInteresado entidad = new ClienteInteresado();
                    entidad.setIdCliente(resultset.getInt(1));
                    entidad.setIdInmueble(resultset.getInt(2));
                    entidad.setFecha_nacicli(resultset.getString(3));
                    entidad.setApellidoCli(resultset.getString(4));
                    entidad.setNombreCli(resultset.getString(5));
                    entidad.setDireccionCli(resultset.getString(6));
                    entidad.setTelefonoCli(resultset.getInt(7));
                    lstClientes.add(entidad);
                }
            }
            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(ClienteIntDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstClientes;
    }

    public ClienteInteresado findOne(ClienteInteresado bean) {
        ClienteInteresado entidad = new ClienteInteresado();

        String sql = SELECT_TIPO_INICIO + bean.getIdCliente();

        try {
            //1º) 
            miMotor.connect();

            ResultSet resultset = miMotor.
                    executeQuery(sql);
            if (resultset != null) {
                while (resultset.next()) {
                    entidad.setIdCliente(resultset.getInt(1));
                    entidad.setIdInmueble(resultset.getInt(2));
                    entidad.setFecha_nacicli(resultset.getString(3));
                    entidad.setApellidoCli(resultset.getString(4));
                    entidad.setNombreCli(resultset.getString(5));
                    entidad.setDireccionCli(resultset.getString(6));
                    entidad.setTelefonoCli(resultset.getInt(7));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            miMotor.disconnect();
        }
        return entidad;
    }

    public int addClientInteresado(ClienteInteresado bean) {

        String sql_filtro = "";
        String sql_final = "";
        int filasAF = 0;
        try {
            this.miMotor.connect();

            if (bean != null) {

                sql_filtro += +bean.getIdInmueble() + "', '"
                        + (bean.getFecha_nacicli() != null ? bean.getFecha_nacicli() : "0000-00-00") + "', '"
                        + (bean.getApellidoCli() != null ? bean.getApellidoCli() : "") + "', '"
                        + (bean.getNombreCli() != null ? bean.getNombreCli() : "") + "', '"
                        + (bean.getDireccionCli() != null ? bean.getDireccionCli() : "") + "', '"
                        + bean.getTelefonoCli() + "')";

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
        ClienteIntDAO clienteIntDAO = new ClienteIntDAO();
        ClienteInteresado cliInteresado = new ClienteInteresado();
        cliInteresado.setIdCliente(1);
        //ArrayList<ClienteInteresado> lstClientes = clienteIntDAO.findOne(cliInteresado);
        System.out.println(ClienteInteresado.fromArrayListToJsonOne(clienteIntDAO.findOne(cliInteresado)));
    }
}
