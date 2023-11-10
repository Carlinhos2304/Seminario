package com.example.seminario;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText login_rut;
    private EditText login_pin;
    private Button boton_login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_rut = findViewById(R.id.login_rut);
        login_pin = findViewById(R.id.login_pin);
        boton_login = findViewById(R.id.boton_login);
        mAuth = FirebaseAuth.getInstance();
        TextView Noregistrado = findViewById(R.id.Noregistrado);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
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
                iniciarSesionPersonalizada(rut,pin);

                }
        });
    }
    private void iniciarSesionPersonalizada(String rut, String pin){
        if(verificarCredencialesEnBD(rut,pin)){
            Intent intent=new Intent(MainActivity.this,MainInicio.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,"Credenciales incorrectas",Toast.LENGTH_SHORT).show();
        }
    }
    private boolean verificarCredencialesEnBD(String rut, String pin) {
        // Supongamos que tienes una referencia a tu base de datos de Firebase
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Estudiantes");

        // Realiza una consulta para encontrar el usuario con el Rut proporcionado
        ref.orderByChild("rut").equalTo(rut).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Para cada usuario con el Rut proporcionado, verifica el pin
                    String pinFromDatabase = userSnapshot.child("pin").getValue(String.class);
                    if (pinFromDatabase != null && pinFromDatabase.equals(pin)) {
                        // Las credenciales son correctas
                        // Retorna true o realiza alguna acción según tu lógica
                        callback.onAutenticacionExitosa();
                        return;
                    }
                }
                callback.onAutenticacionFallida();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de base de datos si es necesario
            }
        });

        // El método es asíncrono, por lo que no puedes retornar directamente aquí
        // Retorna false por defecto o realiza alguna acción según tu lógica
        return false;
    }
    verificarCredencialesEnBD

}