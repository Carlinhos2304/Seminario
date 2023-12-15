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

/**
 * Actividad que muestra los detalles de un token para un estudiante y proporciona la funcionalidad
 * para usar o marcar como perdido el token.
 */
public class MainDetallesToken extends AppCompatActivity {

    /**
     * Método llamado cuando se crea esta actividad.
     *
     * @param savedInstanceState La instancia previamente guardada si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_token_alumno);

        // Obtiene los detalles del estudiante y el token enviados desde la actividad anterior
        UserEstudiante estudiante = getIntent().getParcelableExtra("estudiante");
        UserToken token = getIntent().getParcelableExtra("token");

        // Referencias a los elementos de la interfaz de usuario
        TextView textViewCodigo = findViewById(R.id.textViewCodigo);
        TextView nombreProfesor = findViewById(R.id.nombreprofesor_token);
        TextView recompensa = findViewById(R.id.recompensa);
        TextView fecha = findViewById(R.id.fecha_token);
        Button boton = findViewById(R.id.boton_usar);
        Button botonperdida = findViewById(R.id.boton_perdida);


        // Establece los detalles del token en los elementos de la interfaz de usuario
        String rutFirebase = getIntent().getStringExtra("rut").replace(".", "").replace("-", "");
        textViewCodigo.setText(token.getCodigo());
        nombreProfesor.setText(token.getNombreProfesor());
        recompensa.setText(token.getRecompensa());
        fecha.setText(token.getPlazoEntrega());

        /**
         * ActionListener que maneja el evento de clic para el botón de marcar el token como perdido.
         * Agrega la lógica para marcar el token como perdido si es necesario.
         * Aquí se puede implementar la funcionalidad correspondiente para marcar el token como perdido.
         */
        botonperdida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para dirigirse a MainTokenPerdido
                Intent intent = new Intent(MainDetallesToken.this, MainTokenPerdido.class);

                // Pasar los datos del estudiante, token y rutFirebase a MainTokenPerdido
                intent.putExtra("estudiante", estudiante);
                intent.putExtra("token", token);
                intent.putExtra("rutFirebase", rutFirebase);

                // Iniciar la actividad MainTokenPerdido
                startActivity(intent);
            }
        });

        /**
         * ActionListener que maneja el evento de clic para el botón de usar el token.
         * Este método realiza la lógica para usar el token y actualizar la base de datos.
         * También muestra mensajes indicando si el token se usó correctamente o no.
         */
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para usar el token y actualizar la base de datos
                token.clearRutAlumno();

                DatabaseReference tokenRef= FirebaseDatabase.getInstance().getReference("Profesores")
                        .child(rutFirebase)
                        .child("tokens")
                        .child(token.getCodigo());

                Map<String,Object> updateValues = new HashMap<>();
                updateValues.put("rutAlumno", token.getRutAlumno());

                /**
                 * OnCompleteListener que gestiona el resultado de la actualización de los valores en la base de datos.
                 * Muestra mensajes indicando si el token se usó correctamente o no después de actualizar la base de datos.
                 */
                tokenRef.updateChildren(updateValues).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            // Muestra un mensaje indicando que el token se canjeó correctamente
                            Toast.makeText(MainDetallesToken.this, "Token canjeado correctamente", Toast.LENGTH_SHORT).show();

                            // Cierra la actividad actual
                            finish();

                            // Muestra un mensaje solicitando devolver el Token presencialmente
                            Toast.makeText(MainDetallesToken.this, "Por favor devolver Token presencialmente.", Toast.LENGTH_SHORT).show();

                        } else {
                            // Muestra un mensaje si el token no se pudo usar
                            Toast.makeText(MainDetallesToken.this, "Token no se pudo usar", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
}
