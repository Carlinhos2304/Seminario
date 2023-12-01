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

/**
 * Actividad que muestra la lista de estudiantes asociados a un profesor.
 * Muestra los estudiantes en un RecyclerView y permite ver detalles de cada uno de ellos.
 */
public class MainSeccionProfesor extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private EstudianteAdapter adapter;

    /**
     * Método que se llama cuando la actividad se está iniciando.
     * Se encarga de configurar la interfaz de usuario y obtener los datos de los estudiantes de la base de datos.
     * @param savedInstanceState Objeto Bundle que proporciona datos sobre el estado previamente guardado de la actividad.
     */
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

        /**
         * Configura el listener para manejar los clics en el adaptador de estudiantes.
         * Al hacer clic en un elemento del adaptador, se invoca este listener para abrir una actividad o fragmento con los detalles del estudiante seleccionado.
         * Utiliza el método 'abrirSeccionEspecifica(estudiante)' para mostrar información detallada sobre el estudiante.
         */
        adapter.setOnItemClickListener(new EstudianteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserEstudiante estudiante) {
                // Aquí puedes abrir la nueva actividad o fragmento específico con los datos del estudiante
                abrirSeccionEspecifica(estudiante);
            }

        });

        recyclerView.setAdapter(adapter);


        /**
         * Agrega un escuchador de datos a la referencia de la base de datos.
         * Este escuchador detecta cambios en los datos asociados a la referencia.
         * Al recibir datos nuevos o actualizados, limpia la lista de estudiantes y agrega los datos actualizados obtenidos de la base de datos.
         * Luego, notifica al adaptador que los datos han cambiado para que se refresque la vista.
         * Maneja los errores de cancelación del escuchador en el método 'onCancelled'.
         */
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

    /**
     * Método para abrir una actividad que muestra detalles específicos de un estudiante seleccionado.
     * @param estudiante Objeto UserEstudiante que contiene los detalles del estudiante seleccionado.
     */
    private void abrirSeccionEspecifica(UserEstudiante estudiante) {
        Intent intent = new Intent(MainSeccionProfesor.this, MainDetallesAlumno.class);
        intent.putExtra("estudiante", estudiante);
        startActivity(intent);
    }
}
