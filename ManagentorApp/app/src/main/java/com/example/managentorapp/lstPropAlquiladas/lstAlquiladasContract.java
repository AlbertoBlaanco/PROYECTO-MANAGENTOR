package com.example.managentorapp.lstPropAlquiladas;

import com.example.managentorapp.entitities.Alquilados;
import com.example.managentorapp.entitities.Propiedad;

import java.util.ArrayList;

public interface lstAlquiladasContract {
    public interface View{
        void onSuccesslstPropAlquiladas(ArrayList<Alquilados> lstPropiedad);
        void onFailurelstPropAlquiladas(String err);
    }

    public interface Presenter{
        void getlstPropAlquiladas(String filtro);
    }

    public interface Model{
        interface onlstPropAlquiladasListener{
            void onSuccess(ArrayList<Alquilados> lstPropiedad);
            void onFailure(String err);
        }
        void lstPropAlquiladasWS(String filtro, onlstPropAlquiladasListener onlstPropAlquiladasListener);
    }
}
