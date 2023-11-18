package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainEditarNuevoToken extends AppCompatActivity {

    private Button agregar;
    private EditText editTextCodigoToken; // Agrega esta línea


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_token);
        agregar = findViewById(R.id.buttonAgregarNuevoToken);
        editTextCodigoToken = findViewById(R.id.editTextCodigoToken); // Agrega esta línea




        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarToken();
                Intent intent = new Intent(MainEditarNuevoToken.this, MainTokensProfesor.class);
                startActivity(intent);
            }
        });
    }

    private void agregarToken() {
        // Obtener el código del token desde el EditText
        String codigo = editTextCodigoToken.getText().toString().trim();

        // Obtener el profesor desde MyApplicationData
        UserProfesor profesor = MyApplicationData.getInstance().getProfesor();
        Log.d("MainEditarNuevoToken", "Profesor: " + profesor);

        String rutProfesor = profesor.getRut();

        // Validar que el código no esté vacío
        if (!TextUtils.isEmpty(codigo) && profesor != null) {
            // Crear una instancia de Token con el código
            UserToken token = new UserToken(codigo,rutProfesor, "", "","");

            // Obtener una referencia a la colección "tokens" en la base de datos
            DatabaseReference profesorRef = FirebaseDatabase.getInstance().getReference("Profesores")
                    .child("tokens");

            // Agregar el token a la base de datos
            profesorRef.push().setValue(token);

            // Limpiar el EditText después de agregar el token
            editTextCodigoToken.setText("");
        } else {
            // Mostrar un mensaje de error si el código está vacío o no se ha obtenido el profesor
            Toast.makeText(MainEditarNuevoToken.this, "Ingrese un código de token válido", Toast.LENGTH_SHORT).show();
        }
    }
}

