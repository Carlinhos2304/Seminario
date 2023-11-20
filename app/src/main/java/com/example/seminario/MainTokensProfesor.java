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

public class MainTokensProfesor extends AppCompatActivity {
    private Button agregarOtro;
    private RecyclerView recyclerView;
    private TokenAdapter tokenAdapter;
    private List<UserToken> tokenList;
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

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Manejar el error si es necesario
                }
            });
        }
    }

    }




