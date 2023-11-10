package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListPopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainRegisterProfesor extends AppCompatActivity {

    private Spinner spinner_semestre;
    private RadioGroup radioGroups;
    private TextView Yaregistrado;
    private Button boton_siguiente_pro;
    private DatabaseReference mDatabase;
    private TextView errorTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_profesor);

        spinner_semestre=findViewById(R.id.spinner_semestre);
        radioGroups = findViewById(R.id.radioGroups);
        Yaregistrado =findViewById(R.id.Yaregistrado);
        boton_siguiente_pro = findViewById(R.id.boton_siguiente_pro);
        errorTextView = findViewById(R.id.errorTextView);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Yaregistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent para iniciar la nueva actividad con el diseño "layout_other"
                Intent intent = new Intent(MainRegisterProfesor.this, MainActivity.class);
                startActivity(intent);
            }
        });




        String[] datos = new String[] {"1° Semestre", "2° Semestre", "3° Semestre", "4° Semestre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        spinner_semestre.setAdapter(adapter);



        spinner_semestre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                radioGroups.removeAllViews(); // Elimina las opciones actuales del RadioGroup

                String selectedOption = spinner_semestre.getSelectedItem().toString();
                

                if (selectedOption.equals("1° Semestre")) {
                    // Agrega las opciones específicas para Opción 1
                    CheckBox CheckBox1 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox1.setText("Formación Ciudadana");
                    radioGroups.addView(CheckBox1);

                    CheckBox CheckBox2 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox2.setText("Resolución de Problemas en Álgebra");
                    radioGroups.addView(CheckBox2);

                    CheckBox CheckBox3 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox3.setText("Fundamentos de Base de Datos");
                    radioGroups.addView(CheckBox3);

                    CheckBox CheckBox4 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox4.setText("Introduccion a la Programación");
                    radioGroups.addView(CheckBox4);

                    CheckBox CheckBox5 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox5.setText("Fundamentos de Hardware y Software");
                    radioGroups.addView(CheckBox5);
                    boton_siguiente_pro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String semestre = spinner_semestre.getSelectedItem().toString();
                            ArrayList<String> asignaturas = new ArrayList<>();
                            // Recupera los datos adicionales del Intent
                            String rut = getIntent().getStringExtra("rut");
                            String email = getIntent().getStringExtra("email");
                            String pin = getIntent().getStringExtra("pin");
                            String tipoUsuario = getIntent().getStringExtra("tipoUsuario");
                            if (CheckBox1.isChecked()) {
                                asignaturas.add(CheckBox1.getText().toString());
                            }
                            if (CheckBox2.isChecked()) {
                                asignaturas.add(CheckBox2.getText().toString());
                            }
                            if (CheckBox3.isChecked()) {
                                asignaturas.add(CheckBox3.getText().toString());
                            }
                            if (CheckBox4.isChecked()) {
                                asignaturas.add(CheckBox4.getText().toString());
                            }
                            if (CheckBox5.isChecked()) {
                                asignaturas.add(CheckBox5.getText().toString());
                            }
                            if (!asignaturas.isEmpty()){
                                Intent intent = new Intent(MainRegisterProfesor.this, MainActivity.class);
                                UserProfesor newUser = new UserProfesor(rut, email, pin, tipoUsuario, semestre, asignaturas);
                                String UserPro = mDatabase.child("Profesores").push().getKey();
                                mDatabase.child("Profesores").child(UserPro).setValue(newUser);
                                startActivity(intent);
                            } else {
                                errorTextView.setText("Selecciona al menos una asignatura");
                            }
                        }
                    });


                } else if (selectedOption.equals("2° Semestre")) {
                    // Agrega las opciones específicas para Opción 2
                    CheckBox CheckBox1 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox1.setText("Administración");
                    radioGroups.addView(CheckBox1);

                    CheckBox CheckBox2 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox2.setText("Funciones y Matrices");
                    radioGroups.addView(CheckBox2);

                    CheckBox CheckBox3 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox3.setText("Programación Orientada a Objeto");
                    radioGroups.addView(CheckBox3);

                    CheckBox CheckBox4 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox4.setText("Bases de Datos Relacionales");
                    radioGroups.addView(CheckBox4);

                    CheckBox CheckBox5 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox5.setText("Desarrollo Ágil");
                    radioGroups.addView(CheckBox5);

                    CheckBox CheckBox6 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox6.setText("Sistemas Operativos");
                    radioGroups.addView(CheckBox6);
                    boton_siguiente_pro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String semestre = spinner_semestre.getSelectedItem().toString();
                            ArrayList<String> asignaturas = new ArrayList<>();
                            // Recupera los datos adicionales del Intent
                            String rut = getIntent().getStringExtra("rut");
                            String email = getIntent().getStringExtra("email");
                            String pin = getIntent().getStringExtra("pin");
                            String tipoUsuario = getIntent().getStringExtra("tipoUsuario");
                            if (CheckBox1.isChecked()) {
                                asignaturas.add(CheckBox1.getText().toString());
                            }
                            if (CheckBox2.isChecked()) {
                                asignaturas.add(CheckBox2.getText().toString());
                            }
                            if (CheckBox3.isChecked()) {
                                asignaturas.add(CheckBox3.getText().toString());
                            }
                            if (CheckBox4.isChecked()) {
                                asignaturas.add(CheckBox4.getText().toString());
                            }
                            if (CheckBox5.isChecked()) {
                                asignaturas.add(CheckBox5.getText().toString());
                            }
                            if (CheckBox6.isChecked()) {
                                asignaturas.add(CheckBox6.getText().toString());
                            }
                            if (!asignaturas.isEmpty()){
                                Intent intent = new Intent(MainRegisterProfesor.this, MainActivity.class);
                                UserProfesor newUser = new UserProfesor(rut, email, pin, tipoUsuario, semestre, asignaturas);
                                String UserPro = mDatabase.child("Profesores").push().getKey();
                                mDatabase.child("Profesores").child(UserPro).setValue(newUser);
                                startActivity(intent);
                            } else {
                                errorTextView.setText("Selecciona al menos una asignatura");
                            }
                        }
                    });

                } else if (selectedOption.equals("3° Semestre")) {
                    // Agrega las opciones específicas para Opción 2
                    CheckBox CheckBox1 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox1.setText("Innovación y Emprendimiento I");
                    radioGroups.addView(CheckBox1);

                    CheckBox CheckBox2 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox2.setText("Programación Back End");
                    radioGroups.addView(CheckBox2);

                    CheckBox CheckBox3 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox3.setText("Aplicaciones Móviles para IoT");
                    radioGroups.addView(CheckBox3);

                    CheckBox CheckBox4 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox4.setText("Ingeniería de Software");
                    radioGroups.addView(CheckBox4);

                    CheckBox CheckBox5 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox5.setText("Electivo de Tendencias del Sector Productivo y de Servicios II");
                    radioGroups.addView(CheckBox5);

                    CheckBox CheckBox6 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox6.setText("Taller de Diseño y Desarrollo de Soluciones");
                    radioGroups.addView(CheckBox6);
                    boton_siguiente_pro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String semestre = spinner_semestre.getSelectedItem().toString();
                            ArrayList<String> asignaturas = new ArrayList<>();
                            // Recupera los datos adicionales del Intent
                            String rut = getIntent().getStringExtra("rut");
                            String email = getIntent().getStringExtra("email");
                            String pin = getIntent().getStringExtra("pin");
                            String tipoUsuario = getIntent().getStringExtra("tipoUsuario");
                            if (CheckBox1.isChecked()) {
                                asignaturas.add(CheckBox1.getText().toString());
                            }
                            if (CheckBox2.isChecked()) {
                                asignaturas.add(CheckBox2.getText().toString());
                            }
                            if (CheckBox3.isChecked()) {
                                asignaturas.add(CheckBox3.getText().toString());
                            }
                            if (CheckBox4.isChecked()) {
                                asignaturas.add(CheckBox4.getText().toString());
                            }
                            if (CheckBox5.isChecked()) {
                                asignaturas.add(CheckBox5.getText().toString());
                            }
                            if (CheckBox6.isChecked()) {
                                asignaturas.add(CheckBox6.getText().toString());
                            }
                            if (!asignaturas.isEmpty()){
                                Intent intent = new Intent(MainRegisterProfesor.this, MainActivity.class);
                                UserProfesor newUser = new UserProfesor(rut, email, pin, tipoUsuario, semestre, asignaturas);
                                String UserPro = mDatabase.child("Profesores").push().getKey();
                                mDatabase.child("Profesores").child(UserPro).setValue(newUser);
                                startActivity(intent);
                            } else {
                                errorTextView.setText("Selecciona al menos una asignatura");
                            }
                        }
                    });


                } else if (selectedOption.equals("4° Semestre")) {
                    // Agrega las opciones específicas para Opción 2
                    CheckBox CheckBox1 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox1.setText("Inglés I");
                    radioGroups.addView(CheckBox1);

                    CheckBox CheckBox2 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox2.setText("Programación Front End");
                    radioGroups.addView(CheckBox2);

                    CheckBox CheckBox3 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox3.setText("Bases de Datos No Estructuradas");
                    radioGroups.addView(CheckBox3);

                    CheckBox CheckBox4 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox4.setText("Diseño de Sistemas");
                    radioGroups.addView(CheckBox4);

                    CheckBox CheckBox5 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox5.setText("Electivo de Tendencias del Sector Productivo y de Servicios I");
                    radioGroups.addView(CheckBox5);

                    CheckBox CheckBox6 = new CheckBox(MainRegisterProfesor.this);
                    CheckBox6.setText("Taller de Desarrollo de Aplicaciones");
                    radioGroups.addView(CheckBox6);
                    boton_siguiente_pro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String semestre = spinner_semestre.getSelectedItem().toString();
                            ArrayList<String> asignaturas = new ArrayList<>();
                            // Recupera los datos adicionales del Intent
                            String rut = getIntent().getStringExtra("rut");
                            String email = getIntent().getStringExtra("email");
                            String pin = getIntent().getStringExtra("pin");
                            String tipoUsuario = getIntent().getStringExtra("tipoUsuario");
                            if (CheckBox1.isChecked()) {
                                asignaturas.add(CheckBox1.getText().toString());
                            }
                            if (CheckBox2.isChecked()) {
                                asignaturas.add(CheckBox2.getText().toString());
                            }
                            if (CheckBox3.isChecked()) {
                                asignaturas.add(CheckBox3.getText().toString());
                            }
                            if (CheckBox4.isChecked()) {
                                asignaturas.add(CheckBox4.getText().toString());
                            }
                            if (CheckBox5.isChecked()) {
                                asignaturas.add(CheckBox5.getText().toString());
                            }
                            if (CheckBox6.isChecked()) {
                                asignaturas.add(CheckBox6.getText().toString());
                            }
                            if (!asignaturas.isEmpty()){
                                Intent intent = new Intent(MainRegisterProfesor.this, MainActivity.class);
                                UserProfesor newUser = new UserProfesor(rut, email, pin, tipoUsuario, semestre, asignaturas);
                                String UserPro = mDatabase.child("Profesores").push().getKey();
                                mDatabase.child("Profesores").child(UserPro).setValue(newUser);
                                startActivity(intent);
                            } else {
                                errorTextView.setText("Selecciona al menos una asignatura");
                            }
                        }
                    });

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No se necesita una acción especial aquí
            }





        });




    }





}
