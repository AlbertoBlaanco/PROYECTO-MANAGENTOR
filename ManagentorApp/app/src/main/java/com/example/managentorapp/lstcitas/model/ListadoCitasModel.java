package com.example.managentorapp.lstcitas.model;

import com.example.managentorapp.entitities.Cita;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.lstcitas.ListadoCitasContract;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListadoCitasModel implements ListadoCitasContract.Model {



    @Override
    public void lstCitasWS(String filtro, onListadoCitasListener onListadoCitasListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Cita>> call = apiService.getCitas();
        call.enqueue(new Callback<ArrayList<Cita>>() {
            @Override
            public void onResponse(Call<ArrayList<Cita>> call, Response<ArrayList<Cita>> response) {
                ArrayList<Cita> lstCitas = response.body();
                onListadoCitasListener.onSuccess(lstCitas);
            }

            @Override
            public void onFailure(Call<ArrayList<Cita>> call, Throwable t) {
                onListadoCitasListener.onFailure(t.getMessage());
            }
        });
    }
}
