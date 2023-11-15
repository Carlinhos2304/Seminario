package com.example.seminario;

public class UserEstudiante {

    private String nombre_Apellido;
    private String rut;
    private String email;
    private String pin;
    private String tipoUsuario;
    private String spinner_carrera;
    private String spinner_semestre_es;
    private String spinner_jornada;
    private String spinner_sede;

    public UserEstudiante() {
        // Constructor vacío requerido para Firebase
    }

    public UserEstudiante(String nombre_Apellido, String rut, String email, String pin, String tipoUsuario, String spinner_carrera,String spinner_semestre_es, String spinner_jornada, String spinner_sede) {
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

    public void setSpinner_semestre_es(String spinner_carrera) {
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

}
