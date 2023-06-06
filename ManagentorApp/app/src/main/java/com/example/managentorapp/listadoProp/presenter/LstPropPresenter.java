package com.example.managentorapp.listadoProp.presenter;

import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.listadoProp.model.ListadoPropModel;

import java.util.ArrayList;

public class LstPropPresenter implements ListadoPropContract.Presenter {
    private ListadoPropModel listadoPropModel;
    private ListadoPropContract.View view;

    public LstPropPresenter(ListadoPropContract.View view){
        this.view = view;
        this.listadoPropModel = new ListadoPropModel();
    }



    @Override
    public void getListadoProp(String filtro) {
        listadoPropModel.lstPropiedadWS(filtro, new ListadoPropContract.Model.onListadoPropListener() {
            @Override
            public void onSuccess(ArrayList<Propiedad> lstPropiedad) {
                if(lstPropiedad != null && lstPropiedad.size() > 0){
                        view.onSuccessListadoProp(lstPropiedad);
                }
            }

            @Override
            public void onFailure(String err) {

                view.onFailureListadoProp(err);
            }
        });
    }
}
