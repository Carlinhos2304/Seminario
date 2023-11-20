package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainDetallesToken extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_token_alumno);

        UserToken token = getIntent().getParcelableExtra("token");
        TextView textViewCodigo = findViewById(R.id.textViewCodigo);
        TextView nombreProfesor = findViewById(R.id.nombreprofesor_token);
        TextView recompensa = findViewById(R.id.recompensa);
        TextView fecha = findViewById(R.id.fecha_token);
        Button boton = findViewById(R.id.boton_usar);

        textViewCodigo.setText(token.getCodigo());
        nombreProfesor.setText(token.getNombreProfesor());
        recompensa.setText(token.getRecompensa());
        fecha.setText(token.getPlazoEntrega());

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token.clearRutAlumno();

            }
        });

    }
}
