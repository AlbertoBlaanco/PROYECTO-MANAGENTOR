package com.example.managentorapp.listadoProp;

import com.example.managentorapp.entitities.Propiedad;

import java.util.ArrayList;

public interface ListadoPropContract {
    public interface View{
        void onSuccessListadoProp(ArrayList<Propiedad> lstPropiedad);
        void onFailureListadoProp(String err);
    }

    public interface Presenter{
        void getListadoProp(String filtro);
    }

    public interface Model{
        interface onListadoPropListener{
            void onSuccess(ArrayList<Propiedad> lstPropiedad);
            void onFailure(String err);
        }
        void lstPropiedadWS(String filtro, onListadoPropListener onListadoPropListener);
    }
}
