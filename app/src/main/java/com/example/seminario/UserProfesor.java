package com.example.seminario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa un objeto profesor para la aplicación. Contiene información como el nombre, RUT, email, pin, tipo de usuario,
 * semestre, asignaturas y tokens asociados.
 */
public class UserProfesor {

    // Campos del profesor
    private String nombre_Apellido;
    private String rut;
    private String email;
    private String pin;
    private String tipoUsuario;
    private String spinner_semestre;
    private ArrayList<String> asignaturas;
    private Map<String, UserToken> tokens;


    /**
     * Constructor vacío requerido para Firebase.
     */
    public UserProfesor() {
        // Constructor vacío requerido para Firebase
    }

    /**
     * Constructor de la clase UserProfesor.
     *
     * @param nombre_Apellido Nombre y apellido del profesor.
     * @param rut             RUT del profesor.
     * @param email           Correo electrónico del profesor.
     * @param pin             PIN del profesor.
     * @param tipoUsuario     Tipo de usuario del profesor.
     * @param spinner_semestre Semestre al que pertenece el profesor.
     * @param asignaturas     Lista de asignaturas asociadas al profesor.
     */
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
     * @param nombre_Apellido Nombre y apellido del profesor.
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
     * @param rut RUT del profesor.
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Obtiene el Email del profesor.
     *
     * @return Email del profesor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el Email del profesor.
     *
     * @param email del profesor.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el Pin del profesor.
     *
     * @return Pin del profesor.
     */
    public String getPin() {
        return pin;
    }

    /**
     * Establece el Pin del profesor.
     *
     * @param pin del profesor.
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Obtiene el Tipo de Usuario del profesor.
     *
     * @return TipoUsuario del profesor.
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el Tipo de Usuario del profesor.
     *
     * @param tipoUsuario del profesor.
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Obtiene el semestre del profesor.
     *
     * @return spinner_semestre del profesor.
     */
    public String getSpinner_semestre() {
        return spinner_semestre;
    }

    /**
     * Establece el semestre del profesor.
     *
     * @param spinner_semestre del profesor.
     */
    public void setSpinner_semestre(String spinner_semestre) {
        this.spinner_semestre = spinner_semestre;}

    /**
     * Obtiene la lista de asignaturas del profesor.
     *
     * @return Lista de asignaturas.
     */
    public ArrayList<String> getAsignaturas() {
        return asignaturas;
    }

    /**
     * Establece la lista de asignaturas del profesor.
     *
     * @param asignaturas Lista de asignaturas a establecer.
     */
    public void setAsignaturas(ArrayList<String> asignaturas) {
        this.asignaturas = asignaturas;
    }

    /**
     * Obtiene los tokens asociados al profesor.
     *
     * @return Mapa de tokens asociados al profesor.
     */
    public Map<String, UserToken> getTokens() {
        return tokens;
    }

    /**
     * Establece los tokens asociados al profesor.
     *
     * @param tokens Mapa de tokens asociados al profesor.
     */
    public void setTokens(Map<String, UserToken> tokens) {
        this.tokens = tokens;
    }

    /**
     * Agrega un nuevo token al profesor.
     *
     * @param token Token a agregar.
     */
    public void agregarToken(UserToken token) {
        if (tokens == null) {
            tokens = new HashMap<>();
        }
        tokens.put(token.getCodigo(), token);
    }

    /**
     * Convierte la información del profesor a un objeto Map para Firebase.
     *
     * @return Mapa de datos del profesor.
     */
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
