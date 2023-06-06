package beans;

import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class Propiedad {

    private int ID_PROPIEDAD;
    private String DIRECCION;
    //private int PRECIO;
    private String CIUDAD;
    private int NUM_HAB;
    private int NUM_BANIO;
    private boolean ASCENSOR;
    private int METROS;
    private String IMAGEN;
    private String COMENTARIOS;
    private int DEPOSITO;
    private String FURNISHED;
    private String PROPIETARIO;
    private String ESTADO;
    private int ZIPCODE;

    public Propiedad() {
    }

    public Propiedad(int ID_PROPIEDAD, String DIRECCION,  String CIUDAD, int NUM_HAB, int NUM_BANIO, boolean ASCENSOR, int METROS, String IMAGEN, String COMENTARIOS, int DEPOSITO, String FURNISHED, String PROPIETARIO, String ESTADO, int ZIPCODE) {
        this.ID_PROPIEDAD = ID_PROPIEDAD;
        this.DIRECCION = DIRECCION;
        this.CIUDAD = CIUDAD;
        this.NUM_HAB = NUM_HAB;
        this.NUM_BANIO = NUM_BANIO;
        this.ASCENSOR = ASCENSOR;
        this.METROS = METROS;
        this.IMAGEN = IMAGEN;
        this.COMENTARIOS = COMENTARIOS;
        this.DEPOSITO = DEPOSITO;
        this.FURNISHED = FURNISHED;
        this.PROPIETARIO = PROPIETARIO;
        this.ESTADO = ESTADO;
        this.ZIPCODE = ZIPCODE;
    }

    public int getID_PROPIEDAD() {
        return ID_PROPIEDAD;
    }

    public void setID_PROPIEDAD(int ID_PROPIEDAD) {
        this.ID_PROPIEDAD = ID_PROPIEDAD;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    /*public int getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(int PRECIO) {
        this.PRECIO = PRECIO;
    }*/

    public String getCIUDAD() {
        return CIUDAD;
    }

    public void setCIUDAD(String CIUDAD) {
        this.CIUDAD = CIUDAD;
    }

    public int getNUM_HAB() {
        return NUM_HAB;
    }

    public void setNUM_HAB(int NUM_HAB) {
        this.NUM_HAB = NUM_HAB;
    }

    public int getNUM_BANIO() {
        return NUM_BANIO;
    }

    public void setNUM_BANIO(int NUM_BANIO) {
        this.NUM_BANIO = NUM_BANIO;
    }

    public boolean isASCENSOR() {
        return ASCENSOR;
    }

    public void setASCENSOR(boolean ASCENSOR) {
        this.ASCENSOR = ASCENSOR;
    }

    public int getMETROS() {
        return METROS;
    }

    public void setMETROS(int METROS) {
        this.METROS = METROS;
    }

    public String getIMAGEN() {
        return IMAGEN;
    }

    public void setIMAGEN(String IMAGEN) {
        this.IMAGEN = IMAGEN;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }

    public int getDEPOSITO() {
        return DEPOSITO;
    }

    public void setDEPOSITO(int DEPOSITO) {
        this.DEPOSITO = DEPOSITO;
    }

    public String getFURNISHED() {
        return FURNISHED;
    }

    public void setFURNISHED(String FURNISHED) {
        this.FURNISHED = FURNISHED;
    }

    public String getPROPIETARIO() {
        return PROPIETARIO;
    }

    public void setPROPIETARIO(String PROPIETARIO) {
        this.PROPIETARIO = PROPIETARIO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public int getZIPCODE() {
        return ZIPCODE;
    }

    public void setZIPCODE(int ZIPCODE) {
        this.ZIPCODE = ZIPCODE;
    }

  

    public static String fromArrayListToJson(ArrayList<Propiedad> lstPropiedades) {
        String resp = "[";
        for (Propiedad propiedad : lstPropiedades) {
            resp += "{"
                    + "\"ID_PROPIEDAD\" :\"" + propiedad.getID_PROPIEDAD()+ "\", "
                    + "\"DIRECCION\" : \"" + propiedad.getDIRECCION()+ "\","
                    + "\"CIUDAD\" : \"" + propiedad.getCIUDAD()+ "\","
                    + "\"NUM_HAB\" : \"" + propiedad.getNUM_HAB()+ "\","
                    + "\"NUM_BANIO\" : \"" + propiedad.getNUM_BANIO()+ "\","
                    + "\"ASCENSOR\" : \"" + propiedad.isASCENSOR() + "\","
                    + "\"METROS\" : \"" + propiedad.getMETROS() + "\","
                    + "\"IMAGEN\" : \"" + propiedad.getIMAGEN() + "\","
                    + "\"COMENTARIOS\" : \"" + propiedad.getCOMENTARIOS()+ "\","
                    + "\"DEPOSITO\" : \"" + propiedad.getDEPOSITO()+ "\","
                    + "\"FURNISHED\" : \"" + propiedad.getFURNISHED()+ "\","
                    + "\"PROPIETARIO\" : \"" + propiedad.getPROPIETARIO()+ "\","
                    + "\"ESTADO\" : \"" + propiedad.getESTADO()+ "\","
                    + "\"ZIPCODE\" : \"" + propiedad.getZIPCODE()+ "\"}";
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String fromArrayListToJsonOne(Propiedad propiedad) {
        //String resp = "[";
        String resp = "{"
                + "\"ID_PROPIEDAD\" :\"" + propiedad.getID_PROPIEDAD()+ "\", "
                    + "\"DIRECCION\" : \"" + propiedad.getDIRECCION()+ "\","
                    + "\"CIUDAD\" : \"" + propiedad.getCIUDAD()+ "\","
                    + "\"NUM_HAB\" : \"" + propiedad.getNUM_HAB()+ "\","
                    + "\"NUM_BANIO\" : \"" + propiedad.getNUM_BANIO()+ "\","
                    + "\"ASCENSOR\" : \"" + propiedad.isASCENSOR() + "\","
                    + "\"METROS\" : \"" + propiedad.getMETROS() + "\","
                    + "\"IMAGEN\" : \"" + propiedad.getIMAGEN() + "\","
                    + "\"COMENTARIOS\" : \"" + propiedad.getCOMENTARIOS()+ "\","
                    + "\"DEPOSITO\" : \"" + propiedad.getDEPOSITO()+ "\","
                    + "\"FURNISHED\" : \"" + propiedad.getFURNISHED()+ "\","
                    + "\"PROPIETARIO\" : \"" + propiedad.getPROPIETARIO()+ "\","
                    + "\"ESTADO\" : \"" + propiedad.getESTADO()+ "\","
                    + "\"ZIPCODE\" : \"" + propiedad.getZIPCODE()+ "\"}";
        //resp += "]";

        return resp;

    }

}
