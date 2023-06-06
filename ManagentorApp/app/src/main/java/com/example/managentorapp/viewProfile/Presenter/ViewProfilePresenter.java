package com.example.managentorapp.viewProfile.Presenter;

import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.entitities.Usuario;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.listadoProp.model.ListadoPropModel;
import com.example.managentorapp.viewProfile.ViewProfileContract;
import com.example.managentorapp.viewProfile.model.ViewProfileModel;

import java.util.ArrayList;

public class ViewProfilePresenter implements ViewProfileContract.Presenter {
    private ViewProfileModel viewProfileModel;
    private ViewProfileContract.View view;

    public ViewProfilePresenter(ViewProfileContract.View view){
        this.view = view;
        this.viewProfileModel = new ViewProfileModel();
    }


    @Override
    public void getProfile(int idUser) {
        viewProfileModel.lstPropiedadWS(idUser, new ViewProfileContract.Model.onViewProfileListener() {
            @Override
            public void onSuccess(Usuario user) {
                if(user != null){
                    view.onSuccessProfile(user);
                }
            }

            @Override
            public void onFailure(String err) {
                view.onFailureProfile(err);
            }
        });
    }
}
