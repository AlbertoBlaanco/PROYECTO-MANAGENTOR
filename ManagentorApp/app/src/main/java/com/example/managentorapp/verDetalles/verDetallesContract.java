package com.example.managentorapp.verDetalles;

import com.example.managentorapp.entitities.Propiedad;

import java.util.ArrayList;

public interface verDetallesContract {
    public interface View{
        void onSuccessverDetalles(Propiedad property);
        void onFailureverDetalles(String err);
    }

    public interface Presenter{
        void getDetalles(int idInmueble);
    }

    public interface Model{
        interface onverDetallesListener{
            void onSuccess(Propiedad property);
            void onFailure(String err);
        }
        void verDetallesWS(int idInmueble, onverDetallesListener onverDetallesListener);
    }
}
