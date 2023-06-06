package com.example.managentorapp.verDetalles.presenter;

import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.listadoProp.model.ListadoPropModel;
import com.example.managentorapp.verDetalles.model.DetallesModel;
import com.example.managentorapp.verDetalles.verDetallesContract;

import java.util.ArrayList;

public class DetallesPresenter implements verDetallesContract.Presenter {
    private DetallesModel detallesModel;
    private verDetallesContract.View view;

    public DetallesPresenter(verDetallesContract.View view){
        this.view = view;
        this.detallesModel = new DetallesModel();
    }



    @Override
    public void getDetalles(int idInmueble) {
        detallesModel.verDetallesWS(idInmueble, new verDetallesContract.Model.onverDetallesListener() {
            @Override
            public void onSuccess(Propiedad property) {
                if(property != null){
                    view.onSuccessverDetalles(property);
                }
            }

            @Override
            public void onFailure(String err) {
                view.onFailureverDetalles(err);
            }
        });
    }
}
