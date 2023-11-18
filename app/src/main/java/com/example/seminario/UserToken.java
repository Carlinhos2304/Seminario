package com.example.seminario;

import java.util.ArrayList;

// Token.java
public class UserToken {
    private String codigo;
    private String recompensa;
    private String plazoEntrega;
    private String rutProfesor;
    private String rutAlumno;


    public UserToken() {
        // Constructor vacío requerido para Firebase
    }

    public UserToken(String codigo, String recompensa, String plazoEntrega, String rutProfesor, String rutAlumno) {
        this.codigo = codigo;
        this.rutProfesor = rutProfesor;
        this.rutAlumno = rutAlumno;
        this.recompensa = recompensa;
        this.plazoEntrega = plazoEntrega;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRutProfesor() {
        return rutProfesor;
    }

    public void setRutProfesor(String rutProfesor) {
        this.rutProfesor = rutProfesor;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    public String getPlazoEntrega() {
        return plazoEntrega;
    }

    public void setPlazoEntrega(String plazoEntrega) {
        this.plazoEntrega = plazoEntrega;
    }
    public String getRutAlumno() {
        return rutAlumno;
    }

    public void setRutAlumno(String rutAlumno) {
        this.rutAlumno = rutAlumno;
    }
}

