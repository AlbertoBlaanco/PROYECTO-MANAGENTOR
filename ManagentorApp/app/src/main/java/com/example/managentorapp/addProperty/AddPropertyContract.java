package com.example.managentorapp.addProperty;

import com.example.managentorapp.entitities.Propiedad;

import java.util.ArrayList;

public interface AddPropertyContract {
    public interface View{
        void onSuccessAddProp(ArrayList<Propiedad> lstPropiedad);
        void onFailureAddProperty(String err);
    }

    public interface Presenter{
        void addProp(String filtro);
    }

    public interface Model{
        interface onAddPropListener{
            void onSuccess(ArrayList<Propiedad> lstPropiedad);
            void onFailure(String err);
        }
        void addPropWS(String filtro, onAddPropListener onAddPropListener);
    }
}
