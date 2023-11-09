package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class MainRegister extends AppCompatActivity {

    private Spinner tipo_usuario;
    private Button boton_siguiente;
    private TextView Yaregistrado;

    private EditText register_rut;
    private EditText register_email;
    private EditText register_pin;
    private EditText register_repitapin;

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        tipo_usuario = (Spinner) findViewById(R.id.tipo_usuario);
        boton_siguiente = findViewById(R.id.boton_registrarse);
        Yaregistrado = findViewById(R.id.Yaregistrado);
        register_rut = findViewById(R.id.register_rut);
        register_email = findViewById(R.id.register_email);
        register_pin = findViewById(R.id.register_pin);
        register_repitapin = findViewById(R.id.register_repitapin);

        // Inicializa la instancia de Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

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
                String rut = register_rut.getText().toString();
                String email = register_email.getText().toString();
                String pin = register_pin.getText().toString();
                String selectedOption = tipo_usuario.getSelectedItem().toString();
                if (selectedOption.equals("Estudiante")){
                    // Navega al otro diseño al hacer clic en el botón
                    Intent intent = new Intent(MainRegister.this, MainRegisterEstudiante.class);
                    intent.putExtra("rut", rut);
                    intent.putExtra("email", email);
                    intent.putExtra("pin", pin);
                    intent.putExtra("tipoUsuario", selectedOption);
                    startActivity(intent);

                } else if (selectedOption.equals("Docente")) {
                    // Navega al otro diseño al hacer clic en el botón
                    Intent intent = new Intent(MainRegister.this, MainRegisterProfesor.class);
                    intent.putExtra("rut", rut);
                    intent.putExtra("email", email);
                    intent.putExtra("pin", pin);
                    intent.putExtra("tipoUsuario", selectedOption);
                    startActivity(intent);
                }

            }
        });
        tipo_usuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtener el valor seleccionado
                String selectedTipoUsuario = tipo_usuario.getSelectedItem().toString();
                // Ahora puedes enviar este valor a Firebase
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle nothing selected
            }
        });



    }
}




