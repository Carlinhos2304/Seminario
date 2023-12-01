package com.example.seminario;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Clase que representa un Token de Usuario
 */
public class UserToken implements Parcelable{
    private String codigo;
    private String recompensa;
    private String plazoEntrega;
    private String rutAlumno;
    private String nombreProfesor;


    /**
     * Constructor vacío requerido para Firebase
     */
    public UserToken() {
        // Constructor vacío requerido para Firebase
    }

    /**
     * Constructor con parámetros
     *
     * @param codigo          Código del token
     * @param recompensa      Recompensa asociada al token
     * @param plazoEntrega    Plazo de entrega del token
     * @param rutAlumno       RUT del alumno asociado al token
     * @param nombreProfesor  Nombre del profesor asociado al token
     */
    public UserToken(String codigo, String recompensa, String plazoEntrega, String rutAlumno, String nombreProfesor) {
        this.codigo = codigo;
        this.rutAlumno = rutAlumno;
        this.recompensa = recompensa;
        this.plazoEntrega = plazoEntrega;
        this.nombreProfesor = nombreProfesor;

    }
    /**
     * Obtiene el nombre del profesor asociado al token.
     *
     * @return El nombre del profesor asociado al token
     */
    public String getNombreProfesor() {
        return nombreProfesor;
    }

    /**
     * Establece el nombre del profesor asociado al token.
     *
     * @param nombreProfesor El nombre del profesor a establecer
     */
    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    /**
     * Obtiene el código del token.
     *
     * @return El código del token
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del token.
     *
     * @param codigo El código del token a establecer
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene la recompensa asociada al token.
     *
     * @return La recompensa asociada al token
     */
    public String getRecompensa() {
        return recompensa;
    }

    /**
     * Establece la recompensa asociada al token.
     *
     * @param recompensa La recompensa a establecer
     */
    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    /**
     * Obtiene el plazo de entrega del token.
     *
     * @return El plazo de entrega del token
     */
    public String getPlazoEntrega() {
        return plazoEntrega;
    }

    /**
     * Establece el plazo de entrega del token.
     *
     * @param plazoEntrega El plazo de entrega a establecer
     */
    public void setPlazoEntrega(String plazoEntrega) {
        this.plazoEntrega = plazoEntrega;
    }

    /**
     * Obtiene el RUT del alumno asociado al token.
     *
     * @return El RUT del alumno asociado al token
     */
    public String getRutAlumno() {
        return rutAlumno;
    }

    /**
     * Establece el RUT del alumno asociado al token.
     *
     * @param rutAlumno El RUT del alumno a establecer
     */
    public void setRutAlumno(String rutAlumno) {
        this.rutAlumno = rutAlumno;
    }

    /**
     * Limpia el RUT del alumno asociado al token.
     */
    public void clearRutAlumno() {
        this.rutAlumno = "";
    }

    /**
     * Lee los valores desde el Parcel y asigna a las variables
     */
    protected UserToken(Parcel in) {
        codigo = in.readString();
        nombreProfesor = in.readString();
        recompensa = in.readString();
        plazoEntrega = in.readString();
        rutAlumno = in.readString();
    }

    /**
     * Creador Parcelable
     */
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

    /**
     * Escribe los valores del objeto en el Parcel
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigo);
        dest.writeString(nombreProfesor);
        dest.writeString(recompensa);
        dest.writeString(plazoEntrega);
        dest.writeString(rutAlumno);
    }
}

