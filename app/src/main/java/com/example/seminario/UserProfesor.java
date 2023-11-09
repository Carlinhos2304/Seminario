package com.example.seminario;

public class UserProfesor {
    private String rut;
    private String email;
    private String pin;
    private String tipoUsuario;

    public UserProfesor() {
        // Constructor vacío requerido para Firebase
    }

    public UserProfesor(String rut, String email, String pin, String tipoUsuario) {
        this.rut = rut;
        this.email = email;
        this.pin = pin;
        this.tipoUsuario = tipoUsuario;
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
}
