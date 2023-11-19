package com.example.seminario;

import android.os.Bundle;
import android.widget.TextView;

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
    private RecyclerView recyclerViewTokensEstudiante;
    private TokenAdapter tokenAdapterEstudiante;
    private String rutEstudiante; // Agrega esta variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_estudiante);

        // Obtén referencias a los elementos de la interfaz
        textViewNombreEstudiante = findViewById(R.id.nombre_estudiante);
        recyclerViewTokensEstudiante = findViewById(R.id.recyclerViewTokensEstudiante);

        // Obtén el objeto UserEstudiante de la actividad anterior
        UserEstudiante estudiante = getIntent().getParcelableExtra("estudiante");

        // Verifica si el objeto estudiante y su rut no son nulos
        if (estudiante != null && estudiante.getRut() != null) {
            // Guarda el rut del estudiante para usarlo al obtener los tokens
            rutEstudiante = estudiante.getRut();

            // Resto del código...
        }

        // Configura el RecyclerView
        recyclerViewTokensEstudiante.setLayoutManager(new LinearLayoutManager(this));
        tokenAdapterEstudiante = new TokenAdapter(new ArrayList<>());
        recyclerViewTokensEstudiante.setAdapter(tokenAdapterEstudiante);


        // Guarda el rut del estudiante para usarlo al obtener los tokens
        rutEstudiante = estudiante.getRut();

        // Obtiene y muestra los tokens asociados al estudiante
        obtenerTokensParaRecyclerView();

    }

    private void obtenerTokensParaRecyclerView() {
        // Obtén los tokens asociados al mismo RUT del estudiante
        DatabaseReference tokensRef = FirebaseDatabase.getInstance().getReference("Profesores")
                .child("tokens");

        tokensRef.orderByChild("rutAlumno").equalTo(rutEstudiante).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<UserToken> tokens = new ArrayList<>();
                for (DataSnapshot tokenSnapshot : dataSnapshot.getChildren()) {
                    UserToken token = tokenSnapshot.getValue(UserToken.class);
                    if (token != null) {
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
}
