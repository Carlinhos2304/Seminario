package com.example.seminario;

import android.app.Application;

public class MyApplicationData {
    private static MyApplicationData instance;
    private UserProfesor profesor;

    private MyApplicationData() {
        // Constructor privado para evitar instancias múltiples
    }

    public static synchronized MyApplicationData getInstance() {
        if (instance == null) {
            instance = new MyApplicationData();
        }
        return instance;
    }

    public UserProfesor getProfesor() {
        return profesor;
    }

    public void setProfesor(UserProfesor profesor) {
        this.profesor = profesor;
    }
}
