package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainDetallesToken extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_token_alumno);


        UserEstudiante estudiante = getIntent().getParcelableExtra("estudiante");
        UserToken token = getIntent().getParcelableExtra("token");
        TextView textViewCodigo = findViewById(R.id.textViewCodigo);
        TextView nombreProfesor = findViewById(R.id.nombreprofesor_token);
        TextView recompensa = findViewById(R.id.recompensa);
        TextView fecha = findViewById(R.id.fecha_token);
        Button boton = findViewById(R.id.boton_usar);



        String rutFirebase = getIntent().getStringExtra("rut").replace(".", "").replace("-", "");

        textViewCodigo.setText(token.getCodigo());
        nombreProfesor.setText(token.getNombreProfesor());
        recompensa.setText(token.getRecompensa());
        fecha.setText(token.getPlazoEntrega());

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token.clearRutAlumno();

                DatabaseReference tokenRef= FirebaseDatabase.getInstance().getReference("Profesores")
                        .child(rutFirebase)
                        .child("tokens")
                        .child(token.getCodigo());

                Map<String,Object> updateValues = new HashMap<>();
                updateValues.put("rutAlumno", token.getRutAlumno());

                tokenRef.updateChildren(updateValues).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainDetallesToken.this, "Token canjeado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainDetallesToken.this, MainInicioEs.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(MainDetallesToken.this, "Token no se pudo usar", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
}
