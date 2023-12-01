package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.trusted.Token;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la asignación de tokens a estudiantes por parte de un profesor.
 * Esta clase permite a un profesor asignar tokens a un estudiante específico, definiendo una recompensa y un plazo de entrega.
 */
public class MainReglas extends AppCompatActivity {

    private Spinner spinnerTokens;
    private EditText editTextRecompensa;
    private EditText editTextPlazo;
    private Button botonAsignar;
    private Button Agregar;

    private DatabaseReference databaseReference;
    private String rutFirebase;

    /**
     * Método invocado al crear la actividad. Inicializa los elementos de la interfaz y gestiona la asignación de tokens.
     * @param savedInstanceState Objeto Bundle que contiene el estado anterior de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reglas_token_profesor);

        // Inicializar la referencia a la base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference("Estudiantes");

        // Obtener referencias a los elementos de la interfaz
        spinnerTokens = findViewById(R.id.spinnerTokens);
        editTextRecompensa = findViewById(R.id.editTextRecompensa);
        editTextPlazo = findViewById(R.id.editTextPlazo);
        botonAsignar = findViewById(R.id.botonAsignar);
        Agregar = findViewById(R.id.aSeccionToken);

        // Obtener el objeto UserEstudiante de la actividad anterior
        Intent intent = getIntent();
        UserEstudiante estudiante = intent.getParcelableExtra("estudiante");

        // Obtener el RUT del profesor actual
        UserProfesor profesor = MyApplicationData.getInstance().getProfesor();
        rutFirebase = profesor.getRut().replace(".", "").replace("-", "");





        // Configurar el adaptador para el spinner
        obtenerTokens(rutFirebase);


        /**
         * Maneja el evento de clic en el botón "Agregar", redirigiendo a la actividad MainTokensProfesor.
         */
        Agregar.setOnClickListener(new View.OnClickListener() {
            /**
             * Método invocado al hacer clic en el botón "Agregar".
             * Inicia una nueva actividad MainTokensProfesor para agregar tokens al profesor.
             * @param view La vista que ha sido clickeada (en este caso, el botón "Agregar").
             */
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainReglas.this, MainTokensProfesor.class);
                startActivity(intent1);
            }
        });

        /**
         * Maneja el evento de clic en el botón "botonAsignar".
         * Este evento realiza la asignación de un token al estudiante y redirige a la actividad MainAsignadoCorrectamente.
         */
        botonAsignar.setOnClickListener(new View.OnClickListener() {
            /**
             * Método invocado al hacer clic en el botón "botonAsignar".
             * Asigna un token al estudiante y posteriormente inicia la actividad MainAsignadoCorrectamente.
             * @param view La vista que ha sido clickeada (en este caso, el botón "botonAsignar").
             */
            @Override
            public void onClick(View view) {
                asignarToken(estudiante);
                Intent intent = new Intent(MainReglas.this, MainAsignadoCorrectamente.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Método que obtiene los tokens asociados al profesor actual y los muestra en un Spinner.
     * @param rutFirebase RUT del profesor actual utilizado para recuperar los tokens de la base de datos.
     */
    private void obtenerTokens(String rutFirebase) {

        // Obtener los tokens asociados al mismo RUT del profesor
        DatabaseReference tokensRef = FirebaseDatabase.getInstance().getReference("Profesores")
                .child(rutFirebase)
                .child("tokens");

        /**
         * Escucha los cambios de un solo evento en la referencia de Firebase "tokensRef" a través de un ValueEventListener.
         * Obtiene los códigos de los tokens asociados al profesor con el mismo rutProfesor.
         * @param dataSnapshot Snapshot de datos obtenido desde Firebase, contiene los datos actuales en la ubicación especificada.
         */
        tokensRef.addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             * Método invocado cuando se detecta un cambio en los datos en la referencia de Firebase "tokensRef".
             * Itera sobre los datos para obtener los códigos de los tokens asociados al mismo rutProfesor.
             * @param dataSnapshot Snapshot de datos actualizados.
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> tokenCodes = new ArrayList<>();
                for (DataSnapshot tokenSnapshot : dataSnapshot.getChildren()) {
                    UserToken token = tokenSnapshot.getValue(UserToken.class);
                    if (token != null) {
                        // Solo agregar tokens con el mismo rutProfesor
                        tokenCodes.add(token.getCodigo());
                    }
                }

                // Configurar el adaptador para el spinner con los códigos de los tokens
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainReglas.this, android.R.layout.simple_spinner_item, tokenCodes);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTokens.setAdapter(adapter);
            }

            /**
             * Método invocado si la operación de lectura es cancelada o falla.
             * @param databaseError Objeto que contiene detalles sobre el error de la base de datos.
             */
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar el error si es necesario
                Toast.makeText(MainReglas.this, "Error al obtener tokens", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Método que asigna un token seleccionado a un estudiante con una recompensa y plazo de entrega.
     * @param estudiante Objeto UserEstudiante que representa al estudiante al que se le asignará el token.
     */
    private void asignarToken(UserEstudiante estudiante) {
        // Obtén el código del token seleccionado del Spinner
        String codigoToken = spinnerTokens.getSelectedItem().toString();

        // Verifica que se haya seleccionado un token
        if (codigoToken.isEmpty()) {
            Toast.makeText(MainReglas.this, "Seleccione un token", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtén la referencia del token en la base de datos
        DatabaseReference tokenRef = FirebaseDatabase.getInstance().getReference("Profesores")
                .child(rutFirebase)
                .child("tokens")
                .child(codigoToken);

        // Obtén los valores de recompensa y plazo desde los EditText
        String recompensa = editTextRecompensa.getText().toString().trim();
        String plazo = editTextPlazo.getText().toString().trim();

        // Actualiza los valores del token con la recompensa, plazo y el rut del estudiante
        tokenRef.child("recompensa").setValue(recompensa);
        tokenRef.child("plazoEntrega").setValue(plazo);
        tokenRef.child("rutAlumno").setValue(estudiante.getRut());

        // Elimina el token del Spinner
        eliminarTokenDelSpinner(codigoToken);

        // Vuelve a cargar la lista de tokens en el Spinner
        obtenerTokens(rutFirebase);

        // Informa al usuario que la asignación se realizó con éxito
        Toast.makeText(MainReglas.this, "Token asignado correctamente", Toast.LENGTH_SHORT).show();
    }

    /**
     * Método que elimina un token del Spinner después de ser asignado a un estudiante.
     * @param codigoToken Código del token que se eliminará del Spinner.
     */
    private void eliminarTokenDelSpinner(String codigoToken) {
        /**
         * Ejecuta una operación en el hilo principal de la interfaz de usuario para eliminar un código de token del adaptador del Spinner.
         * @param runnable Runnable que contiene la lógica para eliminar el código del token del adaptador del Spinner.
         */
        runOnUiThread(new Runnable() {
            /**
             * Método que se ejecuta en el hilo principal de la interfaz de usuario para realizar operaciones relacionadas con la interfaz.
             */
            @Override
            public void run() {
                // Remueve el código del token del adaptador del Spinner
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinnerTokens.getAdapter();
                adapter.remove(codigoToken);

                // Notifica al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();
                // Mensaje de log para verificar la eliminación
                Log.d("EliminacionToken", "Token eliminado: " + codigoToken);
            }
        });

}}


