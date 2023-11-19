package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainAsignadoCorrectamente extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.token_asignado_correctamente);
        // Define la duración del retraso en milisegundos (por ejemplo, 3000 ms = 3 segundos)
        int delayMillis = 5000;

        // Crea un objeto Handler para gestionar la tarea con retraso
        Handler handler = new Handler();

        // Programa una tarea con retraso
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Esta parte del código se ejecutará después del tiempo especificado

                // Cambia a la nueva sección
                Intent intent = new Intent(MainAsignadoCorrectamente.this, MainSeccionProfesor.class);
                startActivity(intent);

                // Cierra la actividad actual
                finish();
            }
        }, delayMillis);
    }
}
