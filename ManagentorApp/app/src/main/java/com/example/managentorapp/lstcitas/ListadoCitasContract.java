package com.example.managentorapp.lstcitas;

import com.example.managentorapp.entitities.Cita;
import com.example.managentorapp.entitities.Propiedad;

import java.util.ArrayList;

public interface ListadoCitasContract {
    public interface View{
        void onSuccessListadoCitas(ArrayList<Cita> lstCitas);
        void onFailureListadoCitas(String err);
    }

    public interface Presenter{
        void getListadoCitas(String filtro);
    }

    public interface Model{
        interface onListadoCitasListener{
            void onSuccess(ArrayList<Cita> lstCitas);
            void onFailure(String err);
        }
        void lstCitasWS(String filtro, onListadoCitasListener onListadoCitasListener);
    }
}
