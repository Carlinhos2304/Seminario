package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que maneja el registro de usuarios en la aplicación.
 * Esta actividad permite a los usuarios registrarse como estudiantes o docentes,
 * validando la información ingresada antes de proceder al registro.
 */
public class MainRegister extends AppCompatActivity {

    private Spinner tipo_usuario;
    private Button boton_siguiente;
    private TextView Yaregistrado;

    private EditText register_nombre;
    private EditText register_rut;
    private EditText register_email;
    private EditText register_pin;
    private EditText register_repitapin;

    private DatabaseReference mDatabase;


    /**
     * Método llamado al crear la actividad.
     *
     * @param savedInstanceState Objeto Bundle que contiene el estado previamente guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register_nombre = findViewById(R.id.register_nombre);
        tipo_usuario = findViewById(R.id.tipo_usuario);
        boton_siguiente = findViewById(R.id.boton_registrarse);
        Yaregistrado = findViewById(R.id.Yaregistrado);
        register_rut = findViewById(R.id.register_rut);
        register_email = findViewById(R.id.register_email);
        register_pin = findViewById(R.id.register_pin);
        register_repitapin = findViewById(R.id.register_repitapin);
        TextView MensajeError = findViewById(R.id.MensajeError);

        // Inicializa la instancia de Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String[] datos = new String[]{"Estudiante", "Docente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        tipo_usuario.setAdapter(adapter);

        /**
         * Establece un Listener para el botón "Yaregistrado".
         * Al hacer clic en el botón, inicia una nueva actividad, llevando al usuario de la actividad actual (`MainRegister`)
         * a `MainActivity`.
         */
        Yaregistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent para iniciar la nueva actividad con el diseño "layout_other"
                Intent intent = new Intent(MainRegister.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Establece un Listener para el botón "boton_siguiente".
         * Al hacer clic en el botón, verifica los campos ingresados por el usuario, realiza validaciones y
         * navega a otra actividad en función de la selección del tipo de usuario (Estudiante o Docente).
         * Muestra mensajes de error si algún campo está vacío o no cumple con los formatos requeridos.
         * Si los campos son válidos, inicia una nueva actividad según la selección del tipo de usuario,
         * pasando los datos del registro como parámetros extras en el Intent.
         */
        boton_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre_Apellido = register_nombre.getText().toString();
                String rut = register_rut.getText().toString();
                String email = register_email.getText().toString();
                String pin = register_pin.getText().toString();
                String repitaPin = register_repitapin.getText().toString();
                String selectedOption = tipo_usuario.getSelectedItem().toString();

                if (TextUtils.isEmpty(rut) || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(pin) || TextUtils.isEmpty(repitaPin)) {
                    // Mostrar mensaje de error si algún campo está vacío
                    MensajeError.setText("Completa todos los campos");
                } else if (!isValidRut(rut)) {
                    // Mostrar mensaje de error si el rut no cumple con el formato
                    MensajeError.setText("Formato de Rut invalido");
                } else if (!isValidEmail(email)) {
                    // Mostrar mensaje de error si el correo no cumple con el formato
                    MensajeError.setText("Formato de correo invalido");
                } else if (pin.length() > 6) {
                    MensajeError.setText("El PIN debe tener como maximo 6 caracteres");
                } else if (!pin.equals(repitaPin)) {
                    // Mostrar mensaje de error si las contraseñas no coinciden
                    MensajeError.setText("Las contraseñas no coinciden");
                } else {
                    // Navegar al otro diseño al hacer clic en el botón
                    Intent intent;
                    if (selectedOption.equals("Estudiante")) {
                        intent = new Intent(MainRegister.this, MainRegisterEstudiante.class);
                    } else {
                        intent = new Intent(MainRegister.this, MainRegisterProfesor.class);
                    }

                    // Agregar datos adicionales al intent
                    intent.putExtra("nombre_Apellido", nombre_Apellido);
                    intent.putExtra("rut", rut);
                    intent.putExtra("email", email);
                    intent.putExtra("pin", pin);
                    intent.putExtra("tipoUsuario", selectedOption);
                    startActivity(intent);
                }

            }
        });

        /**
         * Establece un Listener para detectar la selección de un elemento en el Spinner "tipo_usuario".
         * Si se selecciona un elemento, obtiene el valor seleccionado y puede realizar acciones
         * basadas en esa selección, como enviar este valor a Firebase u otras operaciones.
         * Si no se selecciona nada, se maneja la situación donde no se realiza ninguna selección.
         */
        tipo_usuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtener el valor seleccionado
                String selectedTipoUsuario = tipo_usuario.getSelectedItem().toString();
                // Ahora puedes enviar este valor a Firebase
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle nothing selected
            }
        });



    }

    /**
     * Verifica si el formato del RUT ingresado es válido.
     *
     * @param rut El RUT a ser validado.
     * @return true si el RUT tiene el formato correcto, de lo contrario false.
     */
    private boolean isValidRut(String rut) {
        String rutRegex = "^(\\d{1,3}(\\.\\d{3}){2}-[0-9kK])?$";
        Pattern pattern = Pattern.compile(rutRegex);
        Matcher matcher = pattern.matcher(rut);
        return matcher.matches();
    }

    /**
     * Verifica si el formato del correo electrónico ingresado es válido.
     *
     * @param email El correo electrónico a ser validado.
     * @return true si el correo tiene el formato correcto, de lo contrario false.
     */
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}




