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
                Intent intent = new Intent(MainEditarNuevoToken.this, MainSeccionProfesor.class);
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

        // Validar que el código no esté vacío y que el profesor no sea nulo
        if (!TextUtils.isEmpty(codigo) && profesor != null) {
            // Crear una instancia de UserToken con el código
            UserToken token = new UserToken(codigo, profesor.getRut(), "", "", profesor.getnombre_Apellido());
            String rutFirebase = profesor.getRut().replace(".", "").replace("-", "");

            // Agregar el nuevo token directamente a la colección "tokens" con el código como clave
            DatabaseReference profesorRef = FirebaseDatabase.getInstance().getReference("Profesores")
                    .child(rutFirebase);

            // Agregar el nuevo token a la lista de tokens del profesor
            profesorRef.child("tokens").child(codigo).setValue(token);

            // Actualizar el profesor en MyApplicationData
            profesor.agregarToken(token);
            MyApplicationData.getInstance().setProfesor(profesor);


        } else {
            // Mostrar un mensaje de error si el código está vacío o el profesor es nulo
            Toast.makeText(MainEditarNuevoToken.this, "Ingrese un código de token válido", Toast.LENGTH_SHORT).show();
        }
    }
}

