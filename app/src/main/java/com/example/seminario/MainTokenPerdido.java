package com.example.seminario;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainTokenPerdido extends AppCompatActivity {

    private EditText fechaperdida;
    private EditText mensajeperdida;
    private Button botonperdido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.token_perdido);
        fechaperdida=findViewById(R.id.fechaperdida);
        botonperdido=findViewById(R.id.botonNotificar);
        mensajeperdida=findViewById(R.id.editTextMensajePerdida);

        botonperdido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // muestra un mensaje de notificacion exitosa
                Toast.makeText(MainTokenPerdido.this, "Se ha notificado correctamente al Profesor", Toast.LENGTH_SHORT).show();

                // Cierra la actividad actual
                finish();


            }
        });

}}
