package com.example.seminario;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

// Token.java
public class UserToken implements Parcelable{
    private String codigo;
    private String recompensa;
    private String plazoEntrega;
    private String rutAlumno;
    private String nombreProfesor;


    public UserToken() {
        // Constructor vacío requerido para Firebase
    }

    public UserToken(String codigo, String recompensa, String plazoEntrega, String rutAlumno, String nombreProfesor) {
        this.codigo = codigo;
        this.rutAlumno = rutAlumno;
        this.recompensa = recompensa;
        this.plazoEntrega = plazoEntrega;
        this.nombreProfesor = nombreProfesor;

    }
    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    protected UserToken(Parcel in) {
        codigo = in.readString();
        nombreProfesor = in.readString();
        recompensa = in.readString();
        plazoEntrega = in.readString();
        rutAlumno = in.readString();
    }
    // Escribe los valores del objeto en el Parcel

    // Creador Parcelable
    public static final Creator<UserToken> CREATOR = new Creator<UserToken>() {
        @Override
        public UserToken createFromParcel(Parcel in) {
            return new UserToken(in);
        }

        @Override
        public UserToken[] newArray(int size) {
            return new UserToken[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    public void clearRutAlumno() {
        this.rutAlumno = "";
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigo);
        dest.writeString(nombreProfesor);
        dest.writeString(recompensa);
        dest.writeString(plazoEntrega);
        dest.writeString(rutAlumno);
    }
}

