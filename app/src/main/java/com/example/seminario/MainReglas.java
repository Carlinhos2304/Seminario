package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.trusted.Token;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainReglas extends AppCompatActivity {

    private Spinner spinnerTokens;
    private EditText editTextRecompensa;
    private EditText editTextPlazo;
    private Button botonAsignar;
    private Button Agregar;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reglas_token_profesor);

        // Inicializar la referencia a la base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference("Estudiantes");

        // Obtener referencias a los elementos de la interfaz
        spinnerTokens = findViewById(R.id.spinnerTokens);
        editTextRecompensa = findViewById(R.id.editTextRecompensa);
        editTextPlazo = findViewById(R.id.editTextPlazo);
        botonAsignar = findViewById(R.id.botonAsignar);
        Agregar = findViewById(R.id.aSeccionToken);

        // Obtener el objeto UserEstudiante de la actividad anterior
        Intent intent = getIntent();
        UserEstudiante estudiante = intent.getParcelableExtra("estudiante");

        // Configurar el adaptador para el spinner (puedes obtener tus tokens de la base de datos aquí)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obtenerTokens());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTokens.setAdapter(adapter);

        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainReglas.this, MainTokensProfesor.class);
                startActivity(intent1);
            }
        });



        botonAsignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asignarToken(estudiante);
            }
        });
    }
        private void asignarToken( UserEstudiante estudiante) {

        }

        private String[] obtenerTokens() {
            // Aquí puedes obtener los nombres de los tokens de la base de datos
            // Por ahora, simplemente devolveré algunos valores de ejemplo
            return new String[]{"Token1", "Token2", "Token3"};
        }
    }


