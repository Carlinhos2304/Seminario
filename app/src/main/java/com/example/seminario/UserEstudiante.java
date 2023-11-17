package com.example.seminario;

import android.os.Parcel;
import android.os.Parcelable;

public class UserEstudiante implements Parcelable {
    private String nombre_Apellido;
    private String rut;
    private String email;
    private String pin;
    private String tipoUsuario;
    private String spinner_carrera;
    private String spinner_semestre_es;
    private String spinner_jornada;
    private String spinner_sede;


    // Constructor vacío requerido para Firebase
    public UserEstudiante() {}

    public UserEstudiante(String nombre_Apellido, String rut, String email, String pin, String tipoUsuario, String spinner_carrera, String spinner_semestre_es, String spinner_jornada, String spinner_sede) {
        this.nombre_Apellido = nombre_Apellido;
        this.rut = rut;
        this.email = email;
        this.pin = pin;
        this.tipoUsuario = tipoUsuario;
        this.spinner_carrera = spinner_carrera;
        this.spinner_semestre_es = spinner_semestre_es;
        this.spinner_jornada = spinner_jornada;
        this.spinner_sede = spinner_sede;
    }

    // Agrega los métodos getter y setter para acceder a los campos

    public String getnombre_Apellido() {
        return nombre_Apellido;
    }

    public void setnombre_Apellido(String nombre_Apellido) {
        this.nombre_Apellido = nombre_Apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSpinner_carrera() {
        return spinner_carrera;
    }

    public void setSpinner_carrera(String spinner_carrera) {
        this.spinner_carrera = spinner_carrera;
    }

    public String getSpinner_semestre_es() {
        return spinner_semestre_es;
    }

    public void setSpinner_semestre_es(String spinner_semestre_es) {
        this.spinner_semestre_es = spinner_semestre_es;
    }

    public String getSpinner_jornada() {
        return spinner_jornada;
    }

    public void setSpinner_jornada(String spinner_jornada) {
        this.spinner_jornada = spinner_jornada;
    }

    public String getSpinner_sede() {
        return spinner_sede;
    }

    public void setSpinner_sede(String spinner_sede) {
        this.spinner_sede = spinner_sede;
    }

    // Implementación de Parcelable
    protected UserEstudiante(Parcel in) {
        nombre_Apellido = in.readString();
        rut = in.readString();
        email = in.readString();
        pin = in.readString();
        tipoUsuario = in.readString();
        spinner_carrera = in.readString();
        spinner_semestre_es = in.readString();
        spinner_jornada = in.readString();
        spinner_sede = in.readString();
    }

    public static final Creator<UserEstudiante> CREATOR = new Creator<UserEstudiante>() {
        @Override
        public UserEstudiante createFromParcel(Parcel in) {
            return new UserEstudiante(in);
        }

        @Override
        public UserEstudiante[] newArray(int size) {
            return new UserEstudiante[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre_Apellido);
        dest.writeString(rut);
        dest.writeString(email);
        dest.writeString(pin);
        dest.writeString(tipoUsuario);
        dest.writeString(spinner_carrera);
        dest.writeString(spinner_semestre_es);
        dest.writeString(spinner_jornada);
        dest.writeString(spinner_sede);
    }
}
