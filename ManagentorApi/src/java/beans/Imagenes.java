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
public class Imagenes {

    private int ID_IMAGEN;
    private int ID_INMUEBLE;
    private String URL;

    public Imagenes() {
    }

    public Imagenes(int ID_IMAGEN, int ID_INMUEBLE, String URL) {
        this.ID_IMAGEN = ID_IMAGEN;
        this.ID_INMUEBLE = ID_INMUEBLE;
        this.URL = URL;
    }

    public int getID_IMAGEN() {
        return ID_IMAGEN;
    }

    public void setID_IMAGEN(int ID_IMAGEN) {
        this.ID_IMAGEN = ID_IMAGEN;
    }

    public int getID_INMUEBLE() {
        return ID_INMUEBLE;
    }

    public void setID_INMUEBLE(int ID_INMUEBLE) {
        this.ID_INMUEBLE = ID_INMUEBLE;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public static String fromArrayListToJson(ArrayList<Imagenes> lstImagenes) {
        String resp = "[";
        for (Imagenes img : lstImagenes) {
            resp += "{"
                    + "\"ID_IMAGEN\" :\"" + img.getID_IMAGEN() + "\", "
                    + "\"ID_INMUEBLE\" : \"" + img.getID_INMUEBLE() + "\","
                    + "\"URL\" : \"" + img.getURL() + "\"}";
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }
}
