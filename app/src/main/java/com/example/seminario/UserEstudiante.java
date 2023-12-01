package com.example.seminario;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Clase para representar un estudiante, implementa Parcelable para permitir su paso entre componentes de Android.
 */
public class UserEstudiante implements Parcelable {


    // Campos del Estudiante
    private String nombre_Apellido;
    private String rut;
    private String email;
    private String pin;
    private String tipoUsuario;
    private String spinner_carrera;
    private String spinner_semestre_es;
    private String spinner_jornada;
    private String spinner_sede;
    private String rutProfesorAsociado;


    /**
     * Constructor vacío requerido para Firebase.
     */
    public UserEstudiante() {}

    /**
     * Constructor para inicializar un objeto UserEstudiante con los datos proporcionados.
     *
     * @param nombre_Apellido         Nombre y apellido del estudiante.
     * @param rut                     Rut del estudiante.
     * @param email                   Email del estudiante.
     * @param pin                     PIN del estudiante.
     * @param tipoUsuario             Tipo de usuario.
     * @param spinner_carrera         Carrera del estudiante.
     * @param spinner_semestre_es     Semestre del estudiante.
     * @param spinner_jornada         Jornada del estudiante.
     * @param spinner_sede            Sede del estudiante.
     * @param rutProfesorAsociado     Rut del profesor asociado al estudiante.
     */
    public UserEstudiante(String nombre_Apellido, String rut, String email, String pin, String tipoUsuario, String spinner_carrera, String spinner_semestre_es, String spinner_jornada, String spinner_sede, String rutProfesorAsociado) {
        this.nombre_Apellido = nombre_Apellido;
        this.rut = rut;
        this.email = email;
        this.pin = pin;
        this.tipoUsuario = tipoUsuario;
        this.spinner_carrera = spinner_carrera;
        this.spinner_semestre_es = spinner_semestre_es;
        this.spinner_jornada = spinner_jornada;
        this.spinner_sede = spinner_sede;
        this.rutProfesorAsociado = rutProfesorAsociado;
    }

    // Agrega los métodos getter y setter para acceder a los campos
    /**
     * Obtiene el nombre y apellido del profesor.
     *
     * @return Nombre y apellido del profesor.
     */
    public String getnombre_Apellido() {
        return nombre_Apellido;
    }

    /**
     * Establece el nombre y apellido del profesor.
     *
     * @param nombre_Apellido Nombre y apellido del profesor a establecer.
     */
    public void setnombre_Apellido(String nombre_Apellido) {
        this.nombre_Apellido = nombre_Apellido;
    }

    /**
     * Obtiene el RUT del profesor.
     *
     * @return RUT del profesor.
     */
    public String getRut() {
        return rut;
    }

    /**
     * Establece el RUT del profesor.
     *
     * @param rut RUT del profesor a establecer.
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Obtiene el RUT del profesor asociado.
     *
     * @return RUT del profesor asociado.
     */
    public String getRutProfesorAsociado() {
        return rutProfesorAsociado;
    }

    /**
     * Establece el RUT del profesor asociado.
     *
     * @param rutProfesorAsociado RUT del profesor asociado a establecer.
     */
    public void setRutProfesorAsociado(String rutProfesorAsociado) {
        this.rutProfesorAsociado = rutProfesorAsociado;
    }

    /**
     * Obtiene el correo electrónico del profesor.
     *
     * @return Correo electrónico del profesor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del profesor.
     *
     * @param email Correo electrónico del profesor a establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el PIN del profesor.
     *
     * @return PIN del profesor.
     */
    public String getPin() {
        return pin;
    }

    /**
     * Establece el PIN del profesor.
     *
     * @param pin PIN del profesor a establecer.
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Obtiene el tipo de usuario del profesor.
     *
     * @return Tipo de usuario del profesor.
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el tipo de usuario del profesor.
     *
     * @param tipoUsuario Tipo de usuario del profesor a establecer.
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Obtiene la carrera seleccionada por el profesor.
     *
     * @return Carrera seleccionada por el profesor.
     */
    public String getSpinner_carrera() {
        return spinner_carrera;
    }

    /**
     * Establece la carrera seleccionada por el profesor.
     *
     * @param spinner_carrera Carrera seleccionada por el profesor a establecer.
     */
    public void setSpinner_carrera(String spinner_carrera) {
        this.spinner_carrera = spinner_carrera;
    }

    /**
     * Obtiene el semestre seleccionado por el profesor.
     *
     * @return Semestre seleccionado por el profesor.
     */
    public String getSpinner_semestre_es() {
        return spinner_semestre_es;
    }

    /**
     * Establece el semestre seleccionado por el profesor.
     *
     * @param spinner_semestre_es Semestre seleccionado por el profesor a establecer.
     */
    public void setSpinner_semestre_es(String spinner_semestre_es) {
        this.spinner_semestre_es = spinner_semestre_es;
    }

    /**
     * Obtiene la jornada seleccionada por el profesor.
     *
     * @return Jornada seleccionada por el profesor.
     */
    public String getSpinner_jornada() {
        return spinner_jornada;
    }

    /**
     * Establece la jornada seleccionada por el profesor.
     *
     * @param spinner_jornada Jornada seleccionada por el profesor a establecer.
     */
    public void setSpinner_jornada(String spinner_jornada) {
        this.spinner_jornada = spinner_jornada;
    }

    /**
     * Obtiene la sede seleccionada por el profesor.
     *
     * @return Sede seleccionada por el profesor.
     */
    public String getSpinner_sede() {
        return spinner_sede;
    }

    /**
     * Establece la sede seleccionada por el profesor.
     *
     * @param spinner_sede Sede seleccionada por el profesor a establecer.
     */
    public void setSpinner_sede(String spinner_sede) {
        this.spinner_sede = spinner_sede;
    }


    /**
     * Constructor para Parcelable, recibe un Parcel y crea un objeto UserEstudiante.
     *
     * @param in Parcel que contiene los datos para construir el objeto UserEstudiante.
     */
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
        rutProfesorAsociado = in.readString();
    }

    /**
     * Creator necesario para Parcelable.
     */
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
        dest.writeString(rutProfesorAsociado);
    }
}
