package com.example.seminario;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Clase principal que maneja la lógica de inicio de sesión de la aplicación.
 */
public class MainActivity extends AppCompatActivity {

    private EditText login_rut; //Campo para ingresar el RUT
    private EditText login_pin; // Campo para ingresar el PIN
    private Button boton_login; // Botón de inicio de sesión
    private Spinner spinner_usuario; // Selector de tipo de usuario
    private DatabaseReference mDatabase; // Referencia a la base de datos

    /**
     * Método llamado cuando se crea esta actividad.
     *
     * @param savedInstanceState La instancia previamente guardada si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_rut = findViewById(R.id.login_rut);
        login_pin = findViewById(R.id.login_pin);
        boton_login = findViewById(R.id.boton_login);
        spinner_usuario = findViewById(R.id.spinner_usuario);
        TextView Noregistrado = findViewById(R.id.Noregistrado);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Inicialización del spinner con los tipos de usuario
        String[] datos = new String[]{"Estudiante", "Docente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        spinner_usuario.setAdapter(adapter);

        // Acción al hacer clic en el texto de usuario no registrado
        Noregistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent para iniciar la nueva actividad con el diseño "layout_other"
                Intent intent = new Intent(MainActivity.this, MainRegister.class);
                startActivity(intent);
            }
        });

        // Acción al hacer clic en el botón de inicio de sesión
        boton_login.setOnClickListener(new View.OnClickListener() {
            /**
             * Método onClick asociado al botón de inicio de sesión.
             * Verifica las credenciales ingresadas por el usuario y realiza la autenticación.
             * Muestra mensajes de error o inicia la actividad correspondiente según las credenciales.
             *
             * @param v Vista correspondiente al botón presionado.
             */
            @Override
            public void onClick(View v) {

                // Obtiene el RUT y el PIN ingresados por el usuario
                String rut = login_rut.getText().toString();
                String pin = login_pin.getText().toString();

                if (!TextUtils.isEmpty(rut) && !TextUtils.isEmpty(pin)) {

                    // Obtiene el tipo de usuario seleccionado
                    String selectedUsuario = spinner_usuario.getSelectedItem().toString();
                    String dbCollection = (selectedUsuario.equals("Estudiante")) ? "Estudiantes" : "Profesores";

                    // Consulta a la base de datos para verificar las credenciales del usuario
                    mDatabase.child(dbCollection).orderByChild("rut").equalTo(rut).addListenerForSingleValueEvent(new ValueEventListener() {
                        /**
                         * Escucha los cambios en los datos obtenidos de la base de datos.
                         *
                         * @param dataSnapshot Los datos obtenidos de la base de datos.
                         */
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Verifica las credenciales para el usuario encontrado en la colección
                                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                    if (selectedUsuario.equals("Estudiante")) {
                                        // Comprueba las credenciales para estudiantes
                                        UserEstudiante userEstudiante = userSnapshot.getValue(UserEstudiante.class);
                                        if (userEstudiante != null && userEstudiante.getPin().equals(pin)) {
                                            // Credenciales correctas para estudiante
                                            Intent intent = new Intent(MainActivity.this, MainInicioEs.class);

                                            // Envía los datos del estudiante al MainInicioEs
                                            intent.putExtra("estudiante", userEstudiante);
                                            startActivity(intent);
                                        } else {
                                            // PIN incorrecto para estudiante
                                            Toast.makeText(MainActivity.this, "PIN incorrecto", Toast.LENGTH_SHORT).show();
                                        }
                                    } else if (selectedUsuario.equals("Docente")) {
                                        // Comprueba las credenciales para profesores
                                        UserProfesor userProfesor = userSnapshot.getValue(UserProfesor.class);
                                        if (userProfesor != null && userProfesor.getPin().equals(pin)) {

                                            // Configura el profesor en la clase de aplicación personalizada
                                            MyApplicationData.getInstance().setProfesor(userProfesor);

                                            // Credenciales correctas para profesor
                                            Intent intent = new Intent(MainActivity.this, MainSeccionProfesor.class);
                                            startActivity(intent);
                                        } else {
                                            //Muestra mensaje de PIN incorrecto para profesor
                                            Toast.makeText(MainActivity.this, "PIN incorrecto", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            } else {
                                //Muestra un mensaje de Usuario no encontrado en la colección
                                Toast.makeText(MainActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }
                        /**
                         * Maneja la cancelación de la solicitud de datos de la base de datos.
                         *
                         * @param databaseError El error relacionado con la solicitud de datos.
                         */
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Manejar errores de base de datos si es necesario
                        }
                    });
                } else {
                    // Campos de rut y/o pin vacíos
                    Toast.makeText(MainActivity.this, "Ingresa rut y pin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
