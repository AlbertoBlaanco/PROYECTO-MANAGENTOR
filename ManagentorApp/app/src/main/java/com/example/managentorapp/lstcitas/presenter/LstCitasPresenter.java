package com.example.managentorapp.lstcitas.presenter;

import com.example.managentorapp.entitities.Cita;
import com.example.managentorapp.entitities.Propiedad;
import com.example.managentorapp.listadoProp.ListadoPropContract;
import com.example.managentorapp.listadoProp.model.ListadoPropModel;
import com.example.managentorapp.lstcitas.ListadoCitasContract;
import com.example.managentorapp.lstcitas.model.ListadoCitasModel;

import java.util.ArrayList;

public class LstCitasPresenter implements ListadoCitasContract.Presenter {

    private ListadoCitasModel listadoCitasModel;
    private ListadoCitasContract.View view;

    public LstCitasPresenter(ListadoCitasContract.View view){
        this.view = view;
        this.listadoCitasModel = new ListadoCitasModel();
    }

    @Override
    public void getListadoCitas(String filtro) {
            listadoCitasModel.lstCitasWS(filtro, new ListadoCitasContract.Model.onListadoCitasListener() {
                @Override
                public void onSuccess(ArrayList<Cita> lstCitas) {
                    if(lstCitas != null && lstCitas.size() > 0){
                        view.onSuccessListadoCitas(lstCitas);
                    }
                }

                @Override
                public void onFailure(String err) {
                    view.onFailureListadoCitas(err);
                }
            });
    }
}
