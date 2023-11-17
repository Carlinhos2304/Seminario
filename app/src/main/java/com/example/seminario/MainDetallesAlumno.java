package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainDetallesAlumno extends AppCompatActivity {

    private Button boton_asignarToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_alumno_pro);
        boton_asignarToken = findViewById(R.id.boton_asignarToken);


        Intent intent = getIntent();
        UserEstudiante estudiante = intent.getParcelableExtra("estudiante");
        mostrarDetalles(estudiante);}


    private void mostrarDetalles(UserEstudiante estudiante) {
        // Accede a los elementos de la interfaz de usuario (TextViews, etc.) y establece los datos del estudiante
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        TextView textViewCarrera = findViewById(R.id.textViewCarrera);
        TextView textViewRut = findViewById(R.id.textViewRut);
        TextView textViewJornada = findViewById(R.id.textViewJornada);
        TextView textViewSede = findViewById(R.id.textViewSede);
        // Agrega otros elementos según sea necesario

        // Establece los datos del estudiante en los elementos de la interfaz de usuario
        textViewNombre.setText(estudiante.getnombre_Apellido());
        textViewEmail.setText(estudiante.getEmail());
        textViewCarrera.setText(estudiante.getSpinner_carrera());
        textViewRut.setText(estudiante.getRut());
        textViewJornada.setText(estudiante.getSpinner_jornada());
        textViewSede.setText(estudiante.getSpinner_sede());
        // Establece otros datos según sea necesario

        boton_asignarToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDetallesAlumno.this, MainReglas.class);
                intent.putExtra("estudiante", estudiante); // Envía el objeto UserEstudiante
                startActivity(intent);
            }
        });
    }


}
