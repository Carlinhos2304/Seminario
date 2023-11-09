package com.example.seminario;

import java.util.ArrayList;

public class UserProfesor {
    private String rut;
    private String email;
    private String pin;
    private String tipoUsuario;
    private String spinner_semestre;
    private ArrayList<String> asignaturas;


    public UserProfesor() {
        // Constructor vacío requerido para Firebase
    }

    public UserProfesor(String rut, String email, String pin, String tipoUsuario, String spinner_semestre, ArrayList<String> asignaturas) {
        this.rut = rut;
        this.email = email;
        this.pin = pin;
        this.tipoUsuario = tipoUsuario;
        this.spinner_semestre = spinner_semestre;
        this.asignaturas = asignaturas;
    }

    // Agrega los métodos getter y setter para acceder a los campos
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

    public String getSpinner_semestre() {
        return spinner_semestre;
    }

    public void setSpinner_semestre(String spinner_semestre) {
        this.spinner_semestre = spinner_semestre;}
    public ArrayList<String> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<String> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
