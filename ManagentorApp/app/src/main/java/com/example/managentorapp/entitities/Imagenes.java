package com.example.managentorapp.entitities;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class Imagenes {

    @SerializedName("ID_IMAGEN")
    private int ID_IMAGEN;

    @SerializedName("ID_INMUEBLE")
    private int ID_INMUEBLE;

    @SerializedName("URL")
    private String URL;

    private Bitmap photo;
    private boolean main;

    public Imagenes(boolean b, Bitmap decodeResource) {
        this.main = b;
        this.photo = decodeResource;
    }

    public Imagenes(boolean main, Bitmap photo, String url) {
        this(main, photo);
        this.URL = url;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

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


}
