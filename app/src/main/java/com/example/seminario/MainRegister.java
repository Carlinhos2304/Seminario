package com.example.seminario;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainRegister extends AppCompatActivity {

    private Spinner tipo_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        tipo_usuario =(Spinner) findViewById(R.id.tipo_usuario);

        String[] datos = new String[] {"Estudiante", "Docente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        tipo_usuario.setAdapter(adapter);
    }
}

