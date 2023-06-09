package com.example.managentorapp.utils;


import com.example.managentorapp.entitities.Alquilados;
import com.example.managentorapp.entitities.Cita;
import com.example.managentorapp.entitities.Cliente;
import com.example.managentorapp.entitities.Imagenes;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.entitities.Usuario;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("Controller/listadoProp")
    Call<ArrayList<Propiedad>> getPropiedades();

    @GET("Controller/getProp/{idInmueble}")
    Call<Propiedad> getProp(@Path("idInmueble") int idInmueble);

    @GET("Controller/getCliente/{idCliente}")
    Call<Cliente> getCliente(@Path("idCliente") int idCliente);

    @GET("Controller/getCliInt/{idInmueble}")
    Call<ArrayList<Cliente>> getCliInt(@Path("idInmueble") int idInmueble);

    @GET("Controller/getAlquiladas")
    Call<ArrayList<Alquilados>> getAlquiladas();

    @GET("Controller/getCitas")
    Call<ArrayList<Cita>> getCitas();

    @GET("Controller/getProfile/{idUser}")
    Call<Usuario> getProfile(@Path("idUser") int idUser);
    @GET("Controller/getImagenes/{idInmueble}")
    Call<ArrayList<Imagenes>> getImagenes(@Path("idInmueble") int idInmueble);
    @PUT("Controller/editPropiedad")
    Call<Void> editPropiedad(@Body Propiedad propiedad);
    @PUT("Controller/updateEstado")
    Call<Void> updateEstado(@Body Propiedad propiedad);

    @POST("Controller/addPropiedad")
    Call<Void> addPropiedad(@Body Propiedad propiedad);

    @POST("Controller/login")
    Call<Usuario> login(@Body Usuario user);

    @POST("Controller/addClientInt")
    Call<Void> addClientInt(@Body Cliente cliente);
    @POST("Controller/insertUser")
    Call<Void> insertUser(@Body Usuario user);

    @POST("Controller/addCita")
    Call<Void> addCita(@Body Cita cita);
    @POST("Controller/addAlquiler")
    Call<Void> addAlquilados(@Body Alquilados alquilados);

    @POST("Controller/uploadImages")
    Call<Void> uploadImages(@Body String imagenes);

    @POST("Controller/getFilterProp")
    Call<ArrayList<Propiedad>> getFilterProp(@Body RequestBody filtro);

    @POST("Controller/uploadImage/{idInmueble}")
    Call<String> uploadImage(@Body String base64Image, @Path("idInmueble") int idInmueble);

}
