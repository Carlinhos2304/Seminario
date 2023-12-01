package com.example.seminario;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase que representa la pantalla de inicio para el profesor.
 * Esta actividad muestra la interfaz gráfica de la sección principal del profesor.
 */
public class MainInicioPro extends AppCompatActivity {

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Objeto Bundle que contiene el estado previamente guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_profesor);
}}
