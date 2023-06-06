package com.example.managentorapp.viewProfile.model;



import com.example.managentorapp.entitities.Usuario;
import com.example.managentorapp.utils.ApiClient;
import com.example.managentorapp.utils.ApiInterface;
import com.example.managentorapp.viewProfile.ViewProfileContract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewProfileModel implements ViewProfileContract.Model {

    @Override
    public void lstPropiedadWS(int idUser, onViewProfileListener onViewProfileListener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Usuario> call = apiService.getProfile(idUser);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario userRespuesta = response.body();
                onViewProfileListener.onSuccess(userRespuesta);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                onViewProfileListener.onFailure(t.getMessage());
            }
        });
    }
}
