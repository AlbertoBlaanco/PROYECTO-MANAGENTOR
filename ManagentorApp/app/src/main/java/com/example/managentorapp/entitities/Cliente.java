package com.example.managentorapp.entitities;

import com.google.gson.annotations.SerializedName;

public class Cliente {
        @SerializedName("ID_CLIENTE")
        private int idCliente;
        @SerializedName("ID_INMUEBLE")
        private int idInmueble;
        @SerializedName("FECHA_NAC")
        private String Fecha_nacicli;
        @SerializedName("APELLIDOS")
        private String ApellidoCli;
        @SerializedName("NOMBRE")
        private String NombreCli;
        @SerializedName("DIRECCION")
        private String DireccionCli;
        @SerializedName("TELEFONO")
        private int TelefonoCli;

        public Cliente() {
        }

        public Cliente(int idCliente, int idInmueble, String Fecha_nacicli, String ApellidoCli, String NombreCli, String DireccionCli, int TelefonoCli) {
            this.idCliente = idCliente;
            this.idInmueble = idInmueble;
            this.Fecha_nacicli = Fecha_nacicli;
            this.ApellidoCli = ApellidoCli;
            this.NombreCli = NombreCli;
            this.DireccionCli = DireccionCli;
            this.TelefonoCli = TelefonoCli;
        }



    public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public int getIdInmueble() {
            return idInmueble;
        }

        public void setIdInmueble(int idInmueble) {
            this.idInmueble = idInmueble;
        }

        public String getFecha_nacicli() {
            return Fecha_nacicli;
        }

        public void setFecha_nacicli(String Fecha_nacicli) {
            this.Fecha_nacicli = Fecha_nacicli;
        }

        public String getApellidoCli() {
            return ApellidoCli;
        }

        public void setApellidoCli(String ApellidoCli) {
            this.ApellidoCli = ApellidoCli;
        }

        public String getNombreCli() {
            return NombreCli;
        }

        public void setNombreCli(String NombreCli) {
            this.NombreCli = NombreCli;
        }

        public String getDireccionCli() {
            return DireccionCli;
        }

        public void setDireccionCli(String DireccionCli) {
            this.DireccionCli = DireccionCli;
        }

        public int getTelefonoCli() {
            return TelefonoCli;
        }

        public void setTelefonoCli(int TelefonoCli) {
            this.TelefonoCli = TelefonoCli;
        }

    }
