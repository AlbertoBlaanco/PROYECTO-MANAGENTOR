package com.example.managentorapp.listadoProp.model;

import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListadoPropModel implements ListadoPropContract.Model {

    @Override
    public void lstPropiedadWS(String filtro, onListadoPropListener onListadoPropListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Propiedad>> call = apiService.getPropiedades();
        call.enqueue(new Callback<ArrayList<Propiedad>>() {
            @Override
            public void onResponse(Call<ArrayList<Propiedad>> call, Response<ArrayList<Propiedad>> response) {
                ArrayList<Propiedad> lstPropiedades = response.body();
                onListadoPropListener.onSuccess(lstPropiedades);
            }

            @Override
            public void onFailure(Call<ArrayList<Propiedad>> call, Throwable t) {
                onListadoPropListener.onFailure(t.getMessage());
            }
        });
    }
}
