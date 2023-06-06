package com.example.managentorapp.verDetalles.model;

import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.verDetalles.verDetallesContract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetallesModel implements verDetallesContract.Model {


    @Override
    public void verDetallesWS(int idInmueble, onverDetallesListener onverDetallesListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Propiedad> call = apiService.getProp(idInmueble);
        call.enqueue(new Callback<Propiedad>() {
            @Override
            public void onResponse(Call<Propiedad> call, Response<Propiedad> response) {
                Propiedad property = response.body();
                onverDetallesListener.onSuccess(property);
            }

            @Override
            public void onFailure(Call<Propiedad> call, Throwable t) {
                onverDetallesListener.onFailure(t.getMessage());
            }
        });
    }
}
