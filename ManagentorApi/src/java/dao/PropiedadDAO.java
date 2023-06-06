/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.FilterModel;
import beans.Propiedad;
import interfacesDAO.SQLTools;
import java.sql.PreparedStatement;
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
public class PropiedadDAO {

    private SQLTools miMotor = null;
    private static final String SELECT_ALL = "SELECT * FROM inmueble where 1=1 ";
    private static final String SELECT_TIPO_INICIO = "SELECT * FROM inmueble WHERE idInmueble = ";
    private static final String SQL_UPDATE = "UPDATE inmueble SET ";
    private static final String SQL_UPDATE_FINAL = " WHERE idInmueble = ";
    private static final String SQL_ADD = "INSERT INTO inmueble (Titular, CodPostal, Ciudad, Metros, Estado, Precio, Tipo, Direccion, NumBath, NumHabit, hasElevator, descripcion, Imagen) "
            + "VALUES ('";

    public PropiedadDAO() {
        this.miMotor = new MotorMySQL();
    }

    public ArrayList<Propiedad> findAll(Propiedad bean) {
        String sql_filtro = "";
        String sql_final = "";
        ArrayList<Propiedad> lstPropiedades = null;
        try {
            this.miMotor.connect();

            if (bean != null) {
                if (bean.getID_PROPIEDAD() != 0) {
                    sql_filtro += "AND idInmueble='" + bean.getID_PROPIEDAD() + "'";
                }
                if (bean.getPROPIETARIO().length() > 0 && !bean.getPROPIETARIO().equals("") && bean.getPROPIETARIO() != null) {
                    sql_filtro += "AND Titular='" + bean.getPROPIETARIO() + "'";
                }
                if (bean.getZIPCODE() != 0) {
                    sql_filtro += "AND CodPostal='" + bean.getZIPCODE() + "'";
                }
                if (bean.getMETROS() != 0) {
                    sql_filtro += "AND Metros='" + bean.getMETROS() + "'";
                }

                if (bean.getESTADO().length() > 0 && !bean.getESTADO().equals("") && bean.getESTADO() != null) {
                    sql_filtro += "AND Estado='" + bean.getESTADO() + "'";
                }

                /*if (bean.getPRECIO()!= 0) {
                    sql_filtro += "AND Precio='" + bean.getPRECIO() + "'";
                }*/
                if (bean.getFURNISHED().length() > 0 && !bean.getFURNISHED().equals("") && bean.getFURNISHED() != null) {
                    sql_filtro += "AND Tipo='" + bean.getFURNISHED() + "'";
                }

                if (bean.getDIRECCION().length() > 0 && !bean.getDIRECCION().equals("") && bean.getDIRECCION() != null) {
                    sql_filtro += "AND DIRECCION='" + bean.getDIRECCION() + "'";
                }

                if (bean.getNUM_HAB() != 0) {
                    sql_filtro += "AND NumBath='" + bean.getNUM_HAB() + "'";
                }
                if (bean.getNUM_BANIO() != 0) {
                    sql_filtro += "AND NumHabit='" + bean.getNUM_BANIO() + "'";
                }

                if (bean.getCOMENTARIOS().length() > 0 && !bean.getCOMENTARIOS().equals("") && bean.getCOMENTARIOS() != null) {
                    sql_filtro += "AND descripcion='" + bean.getCOMENTARIOS() + "'";
                }

                if (bean.getIMAGEN().length() > 0 && !bean.getIMAGEN().equals("") && bean.getIMAGEN() != null) {
                    sql_filtro += "AND Imagenes='" + bean.getIMAGEN() + "'";
                }

            }

            sql_final = SELECT_ALL + sql_filtro;

            ResultSet resultset = this.miMotor.executeQuery(sql_final);

            if (resultset != null) {
                lstPropiedades = new ArrayList();

                while (resultset.next()) {
                    Propiedad entidad = new Propiedad();
                    entidad.setID_PROPIEDAD(resultset.getInt(1));
                    entidad.setPROPIETARIO(resultset.getString(2));
                    entidad.setZIPCODE(resultset.getInt(3));
                    entidad.setCIUDAD(resultset.getString(4));
                    entidad.setMETROS(resultset.getInt(5));
                    entidad.setESTADO(resultset.getString(6));
                    entidad.setDEPOSITO(resultset.getInt(7));
                    entidad.setFURNISHED(resultset.getString(8));
                    entidad.setDIRECCION(resultset.getString(9));
                    entidad.setNUM_BANIO(resultset.getInt(10));
                    entidad.setNUM_HAB(resultset.getInt(11));
                    entidad.setASCENSOR(resultset.getBoolean(12));
                    entidad.setCOMENTARIOS(resultset.getString(13));

                    lstPropiedades.add(entidad);
                }
            }
            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(PropiedadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstPropiedades;
    }

    public Propiedad findOne(Propiedad propiedad) {
        Propiedad entidad = new Propiedad();

        String sql = SELECT_TIPO_INICIO + propiedad.getID_PROPIEDAD();

        try {
            //1º) 
            miMotor.connect();

            ResultSet resultset = miMotor.
                    executeQuery(sql);
            if (resultset != null) {
                while (resultset.next()) {
                    entidad.setID_PROPIEDAD(resultset.getInt(1));
                    entidad.setPROPIETARIO(resultset.getString(2));
                    entidad.setZIPCODE(resultset.getInt(3));
                    entidad.setCIUDAD(resultset.getString(4));
                    entidad.setMETROS(resultset.getInt(5));
                    entidad.setESTADO(resultset.getString(6));
                    entidad.setDEPOSITO(resultset.getInt(7));
                    entidad.setFURNISHED(resultset.getString(8));
                    entidad.setDIRECCION(resultset.getString(9));
                    entidad.setNUM_BANIO(resultset.getInt(10));
                    entidad.setNUM_HAB(resultset.getInt(11));
                    entidad.setASCENSOR(resultset.getBoolean(12));
                    entidad.setCOMENTARIOS(resultset.getString(13));
                    entidad.setIMAGEN(resultset.getString(14));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            miMotor.disconnect();
        }
        return entidad;
    }

    public int updatePropiedad(Propiedad bean) {
        String sql_filtro = "";
        String sql_final = "";
        int filasAF = 0;
        try {
            this.miMotor.connect();

            if (bean != null) {

                Integer zipcode = bean.getZIPCODE();
                Integer metros = bean.getMETROS();
                Integer deposito = bean.getDEPOSITO();
                Integer num_banio = bean.getNUM_BANIO();
                Integer num_hab = bean.getNUM_HAB();

                if (bean.getPROPIETARIO() != null) {
                    sql_filtro += "Titular='" + bean.getPROPIETARIO() + "'";
                }
                if (zipcode != null) {
                    sql_filtro += ", CodPostal='" + zipcode + "'";
                }

                if (bean.getCIUDAD() != null) {
                    sql_filtro += ", Ciudad='" + bean.getCIUDAD() + "'";
                }

                if (metros != null) {
                    sql_filtro += ", Metros='" + metros + "'";
                }

                if (bean.getESTADO() != null) {
                    sql_filtro += ", Estado='" + bean.getESTADO() + "'";
                }

                if (deposito != null) {
                    sql_filtro += ", Precio='" + deposito + "'";
                }
                if (bean.getFURNISHED() != null) {
                    sql_filtro += ", Tipo='" + bean.getFURNISHED() + "'";
                }

                if (bean.getDIRECCION() != null) {
                    sql_filtro += ", Direccion='" + bean.getDIRECCION() + "'";
                }

                if (num_banio != null) {
                    sql_filtro += ", NumBath='" + num_banio + "'";
                }
                if (num_hab != null) {
                    sql_filtro += ", NumHabit='" + num_hab + "'";
                }

                sql_filtro += ", hasElevator=" + (bean.isASCENSOR() ? 1 : 0);

                if (bean.getCOMENTARIOS() != null) {
                    sql_filtro += ", descripcion='" + bean.getCOMENTARIOS() + "'";
                }

                if (bean.getIMAGEN() != null) {
                    sql_filtro += ", Imagen='" + bean.getIMAGEN() + "'";
                }

            }

            sql_final = SQL_UPDATE + sql_filtro + SQL_UPDATE_FINAL + bean.getID_PROPIEDAD();

            filasAF = miMotor.execute(sql_final);

            //ResultSet resultset = this.miMotor.executeQuery(sql_final);
            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(PropiedadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasAF;

    }

    public int addPropiedad(Propiedad bean) {

        String sql_filtro = "";
        String sql_final = "";
        int filasAF = 0;
        try {
            this.miMotor.connect();

            if (bean != null) {

                sql_filtro += (bean.getPROPIETARIO() != null ? bean.getPROPIETARIO() : "") + "', "
                        + bean.getZIPCODE() + ", '"
                        + (bean.getCIUDAD() != null ? bean.getCIUDAD() : "") + "', "
                        + bean.getMETROS() + ", '"
                        + (bean.getESTADO() != null ? bean.getESTADO() : "") + "', "
                        + bean.getDEPOSITO() + ", '"
                        + (bean.getFURNISHED() != null ? bean.getFURNISHED() : "") + "', '"
                        + (bean.getDIRECCION() != null ? bean.getDIRECCION() : "") + "', "
                        + bean.getNUM_BANIO() + ", "
                        + bean.getNUM_HAB() + ", "
                        + bean.isASCENSOR() + ", '"
                        + (bean.getCOMENTARIOS() != null ? bean.getCOMENTARIOS() : "") + "', '"
                        + (bean.getIMAGEN() != null ? bean.getIMAGEN() : "") + "')";

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

    public ArrayList<Propiedad> filter(FilterModel bean) {
        String sql_filtro = "";
        String sql_final = "";
        ArrayList<Propiedad> lstPropiedades = null;
        try {
            this.miMotor.connect();

            if (bean != null) {

                if (bean.getMaxPrice() != null && bean.getMinPrice() != null) {
                    sql_filtro += "AND Precio > '" + bean.getMinPrice() + "' AND Precio < '" + bean.getMaxPrice() + "'";
                }

                if (bean.getTipo() != null && !bean.getTipo().equals("Cualquiera")) {
                    sql_filtro += " AND Tipo='" + bean.getTipo() + "'";
                }

                if (bean.getBedroomNumber() != null) {
                    sql_filtro += "AND NumHabit='" + bean.getBedroomNumber() + "'";
                }
                if (bean.getBathroomNumber() != null) {
                    sql_filtro += "AND NumBath='" + bean.getBathroomNumber() + "'";
                }

                if (bean.isAscensor()) {
                    sql_filtro += "AND hasElevator='" + 1 + "'";
                } else {
                    sql_filtro += "AND hasElevator='" + 0 + "'";
                }

                if (bean.getMaxSquareMeters() != null && bean.getMinSquareMeters() != null) {
                    sql_filtro += "AND Metros > '" + bean.getMinSquareMeters() + "' AND Metros < '" + bean.getMaxSquareMeters() + "'";
                }

                if (bean.isMascaros()) {
                    sql_filtro += " ORDER BY Precio DESC ";
                } else {
                    sql_filtro += " ORDER BY Precio ASC ";
                }

            }

            sql_final = SELECT_ALL + sql_filtro;
            System.out.println(sql_final);

            ResultSet resultset = this.miMotor.executeQuery(sql_final);

            if (resultset != null) {
                lstPropiedades = new ArrayList();

                while (resultset.next()) {
                    Propiedad entidad = new Propiedad();
                    entidad.setID_PROPIEDAD(resultset.getInt(1));
                    entidad.setPROPIETARIO(resultset.getString(2));
                    entidad.setZIPCODE(resultset.getInt(3));
                    entidad.setCIUDAD(resultset.getString(4));
                    entidad.setMETROS(resultset.getInt(5));
                    entidad.setESTADO(resultset.getString(6));
                    entidad.setDEPOSITO(resultset.getInt(7));
                    entidad.setFURNISHED(resultset.getString(8));
                    entidad.setDIRECCION(resultset.getString(9));
                    entidad.setNUM_BANIO(resultset.getInt(10));
                    entidad.setNUM_HAB(resultset.getInt(11));
                    entidad.setASCENSOR(resultset.getBoolean(12));
                    entidad.setCOMENTARIOS(resultset.getString(13));
                    entidad.setIMAGEN(resultset.getString(14));

                    lstPropiedades.add(entidad);
                }
            }
            this.miMotor.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(PropiedadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstPropiedades;
    }
  
}
