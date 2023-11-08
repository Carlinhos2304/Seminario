package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainRegister extends AppCompatActivity {

    private Spinner tipo_usuario;
    private Button boton_siguiente;
    private TextView Yaregistrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        tipo_usuario = (Spinner) findViewById(R.id.tipo_usuario);
        boton_siguiente = findViewById(R.id.boton_registrarse);
        Yaregistrado = findViewById(R.id.Yaregistrado);

        String[] datos = new String[]{"Estudiante", "Docente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        tipo_usuario.setAdapter(adapter);

        Yaregistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent para iniciar la nueva actividad con el diseño "layout_other"
                Intent intent = new Intent(MainRegister.this, MainActivity.class);
                startActivity(intent);
            }
        });

        boton_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navega al otro diseño al hacer clic en el botón
                Intent intent = new Intent(MainRegister.this, MainRegisterProfesor.class);
                startActivity(intent);
            }
        });


    }
}



