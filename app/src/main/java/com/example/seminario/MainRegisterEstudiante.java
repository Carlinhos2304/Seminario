package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainRegisterEstudiante extends AppCompatActivity {

    private Spinner spinner_carrera;
    private Spinner spinner_semestre_es;
    private Spinner spinner_sede;
    private Spinner spinner_jornada;
    private TextView Yaregistrado;
    private Button boton_registrarse_est;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_estudiante);

        spinner_carrera = findViewById(R.id.spinner_carrera);
        spinner_semestre_es = findViewById(R.id.spinner_semestre_es);
        spinner_sede = findViewById(R.id.spinner_sede);
        spinner_jornada = findViewById(R.id.spinner_jornada);
        boton_registrarse_est = findViewById(R.id.boton_registrarse_est);
        Yaregistrado = findViewById(R.id.Yaregistrado);
        // Inicializa la instancia de Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();




        String[] datos = new String[] {"Ingenieria en Informatica", "Analista Programador"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        spinner_carrera.setAdapter(adapter);

        String[] datos2 = new String[] {"1° Semestre", "2° Semestre", "3° Semestre", "4° Semestre"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos2);
        spinner_semestre_es.setAdapter(adapter2);

        String[] datos3 = new String[] {"Concepcion-Talcahuano"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos3);
        spinner_sede.setAdapter(adapter3);

        String[] datos4 = new String[] {"Jornada Diurna", "Jornada Vespertina"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos4);
        spinner_jornada.setAdapter(adapter4);


        Yaregistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent para iniciar la nueva actividad con el diseño "layout_other"
                Intent intent = new Intent(MainRegisterEstudiante.this, MainActivity.class);
                startActivity(intent);
            }
        });



        boton_registrarse_est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carrera = spinner_carrera.getSelectedItem().toString();
                String semestre = spinner_semestre_es.getSelectedItem().toString();
                String jornada = spinner_jornada.getSelectedItem().toString();
                String sede = spinner_sede.getSelectedItem().toString();

                // Recupera los datos adicionales del Intent
                String nombre_Apellido = getIntent().getStringExtra("nombre_Apellido");
                String rut = getIntent().getStringExtra("rut");
                String email = getIntent().getStringExtra("email");
                String pin = getIntent().getStringExtra("pin");
                String tipoUsuario = getIntent().getStringExtra("tipoUsuario");

                Intent intent = new Intent(MainRegisterEstudiante.this, MainActivity.class);

                DatabaseReference profesoresRef = FirebaseDatabase.getInstance().getReference("Profesores");
                profesoresRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot profesorSnapshot : dataSnapshot.getChildren()) {
                            UserProfesor profesor = profesorSnapshot.getValue(UserProfesor.class);
                            if (profesor != null && semestre.equals(profesor.getSpinner_semestre())) {
                                // Encuentra un profesor con el semestre seleccionado

                                String rutProfesorAsociado = profesor.getRut();
                                // Crea y guarda al estudiante con el rut del profesor asociado
                                UserEstudiante newUser = new UserEstudiante(nombre_Apellido, rut, email, pin, tipoUsuario, carrera, semestre, jornada, sede, rutProfesorAsociado);
                                String UserEs = mDatabase.child("Estudiantes").push().getKey();
                                mDatabase.child("Estudiantes").child(UserEs).setValue(newUser);
                                startActivity(intent);
                                return; // Sale del bucle cuando se encuentra un profesor
                            }
                        }
                        // Manejar el caso cuando no se encuentra un profesor con el semestre seleccionado
                        // Puedes mostrar un mensaje o manejarlo según tus necesidades
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Manejar el error si es necesario
                        // (puedes mostrar un Toast o manejarlo de otra manera)
                    }
                });

            }
        });



    }


}
