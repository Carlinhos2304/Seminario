package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainInicioEs extends AppCompatActivity {
    private TextView textViewNombreEstudiante;

    private DatabaseReference databaseReference;
    private TokenAdapter tokenAdapterEstudiante;
    private String rut; // Agrega esta variable

    private String rutFirebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_estudiante);


        // Obtén referencias a los elementos de la interfaz
        textViewNombreEstudiante = findViewById(R.id.nombre_estudiante);
        RecyclerView recyclerViewTokensEstudiante = findViewById(R.id.recyclerViewTokensEstudiante);
        recyclerViewTokensEstudiante.setLayoutManager(new LinearLayoutManager(this));

        // Obtén el objeto UserEstudiante de la actividad anterior
        UserEstudiante estudiante = getIntent().getParcelableExtra("estudiante");

        // Obtener el RUT del profesor actual
        UserProfesor profesor = MyApplicationData.getInstance().getProfesor();

        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        textViewNombre.setText(estudiante.getnombre_Apellido());
        textViewEmail.setText(estudiante.getEmail());

        // Inicializa el adaptador con una lista vacía
        List<UserToken> tokens = new ArrayList<>();
        tokenAdapterEstudiante = new TokenAdapter(tokens);
        recyclerViewTokensEstudiante.setAdapter(tokenAdapterEstudiante);




        // Guarda el rut del estudiante para usarlo al obtener los tokens
        rut = estudiante.getRut();

        rutFirebase = estudiante.getRutProfesorAsociado().replace(".", "").replace("-", "");


        String cleanedRut = rut.replaceAll("[^a-zA-Z0-9]", "_");

        // Obtén los tokens asociados al mismo RUT del estudiante
        DatabaseReference tokensRef = FirebaseDatabase.getInstance().getReference("Profesores")
                .child(rutFirebase)
                .child("tokens");

        Log.d("FIREBASE_PATH", "Ruta de Firebase: " + tokensRef.toString());

        tokenAdapterEstudiante.setOnItemClickListener(new TokenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserToken token) {
                // Aquí puedes abrir la nueva actividad o fragmento específico con los datos del token
                abrirSeccionEspecifica(token);
            }
        });
        
        // Escucha cambios en los tokens
        tokensRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<UserToken> tokens = new ArrayList<>();

                // Itera sobre los tokens y agrega los que coinciden con el rut del estudiante
                for (DataSnapshot tokenSnapshot : dataSnapshot.getChildren()) {
                    UserToken token = tokenSnapshot.getValue(UserToken.class);
                    if (token != null && token.getRutAlumno().equals(rut)) {
                        tokens.add(token);
                    }
                }

                // Actualiza el adaptador del RecyclerView con la nueva lista de tokens
                tokenAdapterEstudiante.setTokens(tokens);
                tokenAdapterEstudiante.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar el error si es necesario
                // (puedes mostrar un Toast o manejarlo de otra manera)
            }
        });
    }

    private void abrirSeccionEspecifica(UserToken token) {
        Intent intent = new Intent(MainInicioEs.this, MainDetallesToken.class);
        intent.putExtra("estudiante", rut);
        intent.putExtra("rut", rutFirebase);
        intent.putExtra("token", token);
        startActivity(intent);
    }
}
