package com.example.managentorapp.lstPropAlquiladas.model;

import com.example.managentorapp.entitities.Alquilados;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.lstPropAlquiladas.lstAlquiladasContract;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class lstAlquiladasModel implements lstAlquiladasContract.Model {



    @Override
    public void lstPropAlquiladasWS(String filtro, onlstPropAlquiladasListener onlstPropAlquiladasListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Alquilados>> call = apiService.getAlquiladas();
        call.enqueue(new Callback<ArrayList<Alquilados>>() {
            @Override
            public void onResponse(Call<ArrayList<Alquilados>> call, Response<ArrayList<Alquilados>> response) {
                ArrayList<Alquilados> lstPropiedades = response.body();
                onlstPropAlquiladasListener.onSuccess(lstPropiedades);
            }

            @Override
            public void onFailure(Call<ArrayList<Alquilados>> call, Throwable t) {
                onlstPropAlquiladasListener.onFailure(t.getMessage());
            }
        });
    }
}
