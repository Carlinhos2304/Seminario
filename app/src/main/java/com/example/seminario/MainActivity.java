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
                    // Iniciar sesión con Firebase Authentication
                    mAuth.signInWithEmailAndPassword(rut, pin)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Inicio de sesión exitoso
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        // Puedes redirigir a la siguiente actividad
                                        Intent intent = new Intent(MainActivity.this, MainInicio.class);
                                        startActivity(intent);
                                    } else {
                                        // Si el inicio de sesión falla, muestra un mensaje al usuario.
                                        Toast.makeText(MainActivity.this, "Inicio de sesión fallido. Verifica tus credenciales.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Mostrar mensaje de error si los campos están vacíos
                    Toast.makeText(MainActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}