package com.example.seminario;

import android.app.Application;

/**
 * Clase que gestiona los datos de la aplicación y proporciona acceso al profesor actual y su información.
 */
public class MyApplicationData {
    private static MyApplicationData instance;
    private UserProfesor profesor;

    /**
     * Constructor privado para evitar instancias múltiples de esta clase.
     */
    private MyApplicationData() {
        // Constructor privado para evitar instancias múltiples
    }

    /**
     * Obtiene una instancia única de la clase MyApplicationData.
     *
     * @return La instancia única de la clase MyApplicationData.
     */
    public static synchronized MyApplicationData getInstance() {
        if (instance == null) {
            instance = new MyApplicationData();
        }
        return instance;
    }

    /**
     * Obtiene el RUT formateado del profesor actual.
     *
     * @return El RUT del profesor sin puntos ni guiones.
     */
    public String getFormattedRut() {
        if (profesor != null) {
            // Obtener el RUT del profesor
            String rut = profesor.getRut();

            // Eliminar puntos y guiones del RUT
            return rut.replaceAll("[.-]", "");
        }
        return null;
    }


    /**
     * Obtiene el objeto UserProfesor que representa al profesor actual.
     *
     * @return El objeto UserProfesor que contiene la información del profesor actual.
     */
    public UserProfesor getProfesor() {
        return profesor;
    }

    /**
     * Establece el objeto UserProfesor que representa al profesor actual.
     *
     * @param profesor El objeto UserProfesor que contiene la información del profesor actual.
     */
    public void setProfesor(UserProfesor profesor) {
        this.profesor = profesor;
    }
}
