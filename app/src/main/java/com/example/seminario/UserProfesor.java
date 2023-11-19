package com.example.seminario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfesor {

    private String nombre_Apellido;
    private String rut;
    private String email;
    private String pin;
    private String tipoUsuario;
    private String spinner_semestre;
    private ArrayList<String> asignaturas;
    private Map<String, UserToken> tokens;



    public UserProfesor() {
        // Constructor vacío requerido para Firebase
    }

    public UserProfesor(String nombre_Apellido, String rut, String email, String pin, String tipoUsuario, String spinner_semestre, ArrayList<String> asignaturas) {
        this.nombre_Apellido = nombre_Apellido;
        this.rut = rut;
        this.email = email;
        this.pin = pin;
        this.tipoUsuario = tipoUsuario;
        this.spinner_semestre = spinner_semestre;
        this.asignaturas = asignaturas;
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
    // Resto de los métodos getter y setter

    public Map<String, UserToken> getTokens() {
        return tokens;
    }

    public void setTokens(Map<String, UserToken> tokens) {
        this.tokens = tokens;
    }

    public void agregarToken(UserToken token) {
        if (tokens == null) {
            tokens = new HashMap<>();
        }
        tokens.put(token.getCodigo(), token);
    }
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("nombre_Apellido", nombre_Apellido);
        result.put("rut", rut);
        result.put("email", email);
        result.put("pin", pin);
        result.put("tipoUsuario", tipoUsuario);
        result.put("spinner_semestre", spinner_semestre);
        result.put("asignaturas", asignaturas);
        return result;
    }
}
