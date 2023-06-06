package beans;

import java.util.ArrayList;

public class Alquilados {

    private int id_alquilados;
    private int ID_INMUEBLE;
    private int ID_CLIENTE;
    private String FECHA;

    public Alquilados() {
    }

    public Alquilados(int id_alquilados, int id_inmueble, int id_cliente, String fecha) {
        this.id_alquilados = id_alquilados;
        this.ID_INMUEBLE = id_inmueble;
        this.ID_CLIENTE = id_cliente;
        this.FECHA = fecha;
    }

    public int getId_alquilados() {
        return id_alquilados;
    }

    public void setId_alquilados(int id_alquilados) {
        this.id_alquilados = id_alquilados;
    }

    public int getId_inmueble() {
        return ID_INMUEBLE;
    }

    public void setId_inmueble(int id_inmueble) {
        this.ID_INMUEBLE = id_inmueble;
    }

    public int getId_cliente() {
        return ID_CLIENTE;
    }

    public void setId_cliente(int id_cliente) {
        this.ID_CLIENTE = id_cliente;
    }

    public String getFecha() {
        return FECHA;
    }

    public void setFecha(String fecha) {
        this.FECHA = fecha;
    }

    public static String fromArrayListToJson(ArrayList<Alquilados> lstPisosAlquilados) {
        String resp = "[";
        for (Alquilados alquilados : lstPisosAlquilados) {
            resp += "{"
                    + "\"ID_ALQUILADOS\" :\"" + alquilados.getId_alquilados() + "\", "
                    + "\"ID_CLIENTE\" : \"" + alquilados.getId_cliente() + "\","
                    + "\"ID_INMUEBLE\" : \"" + alquilados.getId_inmueble() + "\","
                    + "\"FECHA\" : \"" + alquilados.getFecha() + "\"}";
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

}
