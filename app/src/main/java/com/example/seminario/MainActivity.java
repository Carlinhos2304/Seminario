package com.example.seminario;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText login_rut;
    private EditText login_pin;
    private Button boton_login;
    private Spinner spinner_usuario;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_rut = findViewById(R.id.login_rut);
        login_pin = findViewById(R.id.login_pin);
        boton_login = findViewById(R.id.boton_login);
        spinner_usuario = findViewById(R.id.spinner_usuario);
        TextView Noregistrado = findViewById(R.id.Noregistrado);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Initialize Firebase Auth


        String[] datos = new String[]{"Estudiante", "Docente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        spinner_usuario.setAdapter(adapter);
        Noregistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent para iniciar la nueva actividad con el diseño "layout_other"
                Intent intent = new Intent(MainActivity.this, MainRegister.class);
                startActivity(intent);
            }
        });
        boton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rut = login_rut.getText().toString();
                String pin = login_pin.getText().toString();

                if (!TextUtils.isEmpty(rut) && !TextUtils.isEmpty(pin)) {
                    String selectedUsuario = spinner_usuario.getSelectedItem().toString();
                    String dbCollection = (selectedUsuario.equals("Estudiante")) ? "Estudiantes" : "Profesores";

                    mDatabase.child(dbCollection).orderByChild("rut").equalTo(rut).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Usuario encontrado en la colección
                                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                    if (selectedUsuario.equals("Estudiante")) {
                                        UserEstudiante userEstudiante = userSnapshot.getValue(UserEstudiante.class);
                                        if (userEstudiante != null && userEstudiante.getPin().equals(pin)) {
                                            // Credenciales correctas para estudiante
                                            Intent intent = new Intent(MainActivity.this, MainInicioEs.class);
                                            startActivity(intent);
                                        } else {
                                            // PIN incorrecto para estudiante
                                            Toast.makeText(MainActivity.this, "PIN incorrecto", Toast.LENGTH_SHORT).show();
                                        }
                                    } else if (selectedUsuario.equals("Docente")) {
                                        UserProfesor userProfesor = userSnapshot.getValue(UserProfesor.class);
                                        if (userProfesor != null && userProfesor.getPin().equals(pin)) {
                                            // Credenciales correctas para profesor
                                            Intent intent = new Intent(MainActivity.this, MainInicioPro.class);
                                            startActivity(intent);
                                        } else {
                                            // PIN incorrecto para profesor
                                            Toast.makeText(MainActivity.this, "PIN incorrecto", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            } else {
                                // Usuario no encontrado en la colección
                                Toast.makeText(MainActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Manejar errores de base de datos si es necesario
                        }
                    });
                } else {
                    // Campos de rut y/o pin vacíos
                    Toast.makeText(MainActivity.this, "Ingresa rut y pin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
