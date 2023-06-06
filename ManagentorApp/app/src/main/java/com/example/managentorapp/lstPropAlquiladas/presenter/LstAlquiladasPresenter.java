package com.example.managentorapp.lstPropAlquiladas.presenter;

import com.example.managentorapp.entitities.Alquilados;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.listadoProp.model.ListadoPropModel;
import com.example.managentorapp.lstPropAlquiladas.lstAlquiladasContract;
import com.example.managentorapp.lstPropAlquiladas.model.lstAlquiladasModel;

import java.util.ArrayList;

public class LstAlquiladasPresenter implements lstAlquiladasContract.Presenter {
    private lstAlquiladasModel lstAlquiladasModel;
    private lstAlquiladasContract.View view;

    public LstAlquiladasPresenter(lstAlquiladasContract.View view){
        this.view = view;
        this.lstAlquiladasModel = new lstAlquiladasModel();
    }


    @Override
    public void getlstPropAlquiladas(String filtro) {
        lstAlquiladasModel.lstPropAlquiladasWS(filtro, new lstAlquiladasContract.Model.onlstPropAlquiladasListener() {
            @Override
            public void onSuccess(ArrayList<Alquilados> lstPropiedad) {
                if(lstPropiedad.size() > 0 && lstPropiedad != null){
                    view.onSuccesslstPropAlquiladas(lstPropiedad);
                }
            }

            @Override
            public void onFailure(String err) {
                view.onFailurelstPropAlquiladas(err);
            }
        });
    }
}
