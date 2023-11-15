package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
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

public class MainSeccionProfesor extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private EstudianteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seccion_alumnos_profesor);

        // Obtén una referencia a la base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference("Estudiantes");

        // Configura el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa el adaptador con una lista vacía
        List<UserEstudiante> estudiantes = new ArrayList<>();
        adapter = new EstudianteAdapter(estudiantes);
        recyclerView.setAdapter(adapter);


        adapter = new EstudianteAdapter(estudiantes);

        // Configura el escuchador de clic en el adaptador
        adapter.setOnItemClickListener(new EstudianteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserEstudiante estudiante) {
                // Aquí puedes abrir la nueva actividad o fragmento específico con los datos del estudiante
                abrirSeccionEspecifica(estudiante);
            }

        });

        recyclerView.setAdapter(adapter);

        // Agrega un escuchador de datos
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                estudiantes.clear(); // Limpia la lista antes de agregar nuevos datos

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserEstudiante estudiante = dataSnapshot.getValue(UserEstudiante.class);
                    if (estudiante != null) {
                        estudiantes.add(estudiante);
                    }
                }

                adapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar errores aquí
            }
        });
    }

    private void abrirSeccionEspecifica(UserEstudiante estudiante) {
        Intent intent = new Intent(MainSeccionProfesor.this, MainDetallesAlumno.class);
        intent.putExtra("estudiante", (CharSequence) estudiante);
        startActivity(intent);
    }
}
