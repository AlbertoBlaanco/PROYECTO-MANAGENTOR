package com.example.managentorapp.viewProfile;


import com.example.managentorapp.entitities.Usuario;

public interface ViewProfileContract {
    public interface View{
        void onSuccessProfile(Usuario user);
        void onFailureProfile(String err);
    }

    public interface Presenter{
        void getProfile(int idUser);
    }

    public interface Model{
        interface onViewProfileListener{
            void onSuccess(Usuario user);
            void onFailure(String err);
        }
        void lstPropiedadWS(int idUser, onViewProfileListener onViewProfileListener);
    }
}
