package beans;

import java.util.ArrayList;

public class ClienteInteresado {

    private int ID_CLIENTE;
    private int ID_INMUEBLE;
    private String Fecha_nacicli;
    private String ApellidoCli;
    private String NOMBRE;
    private String DireccionCli;
    private int TELEFONO;

    public ClienteInteresado() {
    }

    public ClienteInteresado(int idCliente, int idInmueble, String Fecha_nacicli, String ApellidoCli, String NombreCli, String DireccionCli, int TelefonoCli) {
        this.ID_CLIENTE = idCliente;
        this.ID_INMUEBLE = idInmueble;
        this.Fecha_nacicli = Fecha_nacicli;
        this.ApellidoCli = ApellidoCli;
        this.NOMBRE = NombreCli;
        this.DireccionCli = DireccionCli;
        this.TELEFONO = TelefonoCli;
    }

    public int getIdCliente() {
        return ID_CLIENTE;
    }

    public void setIdCliente(int idCliente) {
        this.ID_CLIENTE = idCliente;
    }

    public int getIdInmueble() {
        return ID_INMUEBLE;
    }

    public void setIdInmueble(int idInmueble) {
        this.ID_INMUEBLE = idInmueble;
    }

    public String getFecha_nacicli() {
        return Fecha_nacicli;
    }

    public void setFecha_nacicli(String Fecha_nacicli) {
        this.Fecha_nacicli = Fecha_nacicli;
    }

    public String getApellidoCli() {
        return ApellidoCli;
    }

    public void setApellidoCli(String ApellidoCli) {
        this.ApellidoCli = ApellidoCli;
    }

    public String getNombreCli() {
        return NOMBRE;
    }

    public void setNombreCli(String NombreCli) {
        this.NOMBRE = NombreCli;
    }

    public String getDireccionCli() {
        return DireccionCli;
    }

    public void setDireccionCli(String DireccionCli) {
        this.DireccionCli = DireccionCli;
    }

    public int getTelefonoCli() {
        return TELEFONO;
    }

    public void setTelefonoCli(int TelefonoCli) {
        this.TELEFONO = TelefonoCli;
    }

    public static String fromArrayListToJson(ArrayList<ClienteInteresado> lstClientesInteresados) {
        String resp = "[";
        for (ClienteInteresado cli : lstClientesInteresados) {
            resp += "{"
                    + "\"ID_CLIENTE\" :\"" + cli.getIdCliente() + "\", "
                    + "\"ID_INMUEBLE\" : \"" + cli.getIdInmueble() + "\","
                    + "\"FECHA_NAC\" : \"" + cli.getFecha_nacicli() + "\","
                    + "\"APELLIDOS\" : \"" + cli.getApellidoCli() + "\","
                    + "\"NOMBRE\" : \"" + cli.getNombreCli() + "\","
                    + "\"DIRECCION\" : \"" + cli.getDireccionCli() + "\","
                    + "\"TELEFONO\" : \"" + cli.getTelefonoCli() + "\"}";
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String fromArrayListToJsonOne(ClienteInteresado cli) {

        String resp = "{"
                + "\"ID_CLIENTE\" :\"" + cli.getIdCliente() + "\", "
                + "\"ID_INMUEBLE\" : \"" + cli.getIdInmueble() + "\","
                + "\"FECHA_NAC\" : \"" + cli.getFecha_nacicli() + "\","
                + "\"APELLIDOS\" : \"" + cli.getApellidoCli() + "\","
                + "\"NOMBRE\" : \"" + cli.getNombreCli() + "\","
                + "\"DIRECCION\" : \"" + cli.getDireccionCli() + "\","
                + "\"TELEFONO\" : \"" + cli.getTelefonoCli() + "\"}";

        return resp;
    }

}
