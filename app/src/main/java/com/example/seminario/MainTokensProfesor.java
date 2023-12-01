package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
 * La clase MainTokensProfesor es responsable de mostrar los tokens asociados a un profesor específico.
 * Configura un RecyclerView para mostrar la lista de tokens asociados al profesor recuperados desde Firebase Realtime Database.
 * Permite agregar un nuevo token al hacer clic en el botón correspondiente.
 * Obtén el profesor desde MyApplicationData y utiliza su información para acceder a los tokens asociados en la base de datos.
 * El RecyclerView muestra una lista de tokens y actualiza dinámicamente la vista al agregar nuevos tokens.
 * Maneja la cancelación de la consulta a la base de datos en caso de errores en el método 'onCancelled'.
 */
public class MainTokensProfesor extends AppCompatActivity {
    private Button agregarOtro;
    private RecyclerView recyclerView;
    private TokenAdapter tokenAdapter;
    private List<UserToken> tokenList;

    /**
     * Método llamado cuando se crea la actividad MainTokensProfesor.
     * Configura la vista de la actividad con el diseño definido en 'mis_tokens_profesor.xml'.
     * Recupera la referencia al botón 'boton_agregar_token' y establece un escuchador de clic para manejar eventos.
     *
     * @param savedInstanceState Objeto Bundle que contiene el estado anterior de la actividad, si está disponible.
     *                            Puede ser nulo si la actividad se inicia por primera vez.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_tokens_profesor);
        agregarOtro = findViewById(R.id.boton_agregar_token);



        recyclerView = findViewById(R.id.recyclerViewToken); // Reemplaza con el ID correcto del RecyclerView en tu diseño
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        
        // Obtener el profesor desde MyApplicationData
        UserProfesor profesor = MyApplicationData.getInstance().getProfesor();
        String rutFirebase = profesor.getRut().replace(".", "").replace("-", "");

        /**
         * Establece un escuchador de clic para el botón 'agregarOtro'. Al hacer clic en el botón,
         * se crea un nuevo Intent para iniciar la actividad 'MainEditarNuevoToken'.
         * Esta acción inicia la nueva actividad para editar o agregar un nuevo token.
         */
        agregarOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainTokensProfesor.this, MainEditarNuevoToken.class);
                startActivity(intent);
            }
        });

        // Validar que se haya obtenido el profesor
        if (profesor != null) {
            // Configurar el RecyclerView y su adaptador
            tokenList = new ArrayList<>();
            tokenAdapter = new TokenAdapter(tokenList);
            recyclerView.setAdapter(tokenAdapter);

            // Obtener solo los tokens asociados al profesor específico
            DatabaseReference tokensRef = FirebaseDatabase.getInstance().getReference("Profesores")
                    .child(rutFirebase)
                    .child("tokens");


            /**
             * Establece un escuchador para obtener los tokens específicos de un profesor desde la base de datos.
             * Limpia la lista 'tokenList', luego itera a través de los tokens obtenidos en 'dataSnapshot' y los agrega a 'tokenList'.
             * Finalmente, notifica al adaptador 'tokenAdapter' que los datos han cambiado utilizando 'notifyDataSetChanged'.
             *
             * @param dataSnapshot Los datos de los tokens obtenidos desde la base de datos.
             */
            tokensRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    tokenList.clear();

                    for (DataSnapshot tokenSnapshot : dataSnapshot.getChildren()) {
                        UserToken token = tokenSnapshot.getValue(UserToken.class);
                        if (token != null) {
                            tokenList.add(token);


                        }
                    }

                    // Notificar al adaptador que los datos han cambiado
                    tokenAdapter.notifyDataSetChanged();


                }

                /**
                 * Este método se activa cuando se produce la cancelación de la solicitud de datos desde la base de datos.
                 * Proporciona un lugar para manejar cualquier error o situación de cancelación que ocurra durante la solicitud.
                 *
                 * @param databaseError El error que se produce al intentar acceder a los datos desde la base de datos.
                 */
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Manejar el error si es necesario
                }
            });
        }
    }

    }




