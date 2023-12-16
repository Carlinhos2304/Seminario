package com.example.seminario;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Actividad que permite a un profesor agregar un nuevo token a su lista de tokens.
 * Los profesores pueden agregar tokens nuevos proporcionando un código de token válido.
 */
public class MainEditarNuevoToken extends AppCompatActivity {

    private Button agregar;
    private Button QR;
    private EditText editTextCodigoToken; // Agrega esta línea
    private static final int REQUEST_CODE_SCAN = 123; // Código de solicitud para el escaneo de QR


    /**
     * Método llamado cuando se crea esta actividad.
     *
     * @param savedInstanceState La instancia previamente guardada si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_token);
        agregar = findViewById(R.id.buttonAgregarNuevoToken);
        QR = findViewById(R.id.QR);
        editTextCodigoToken = findViewById(R.id.editTextCodigoToken); // Agrega esta línea

        /**
         * Establece un Listener para el botón "agregar" que ejecuta la acción de agregar un token
         * y redirige al profesor a la sección principal de profesor después de agregar el token.
         */
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarToken();// Agrega un token utilizando el método agregarToken()
                Intent intent = new Intent(MainEditarNuevoToken.this, MainSeccionProfesor.class);
                startActivity(intent);// Inicia la actividad MainSeccionProfesor
            }
        });

        QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llama al método para iniciar el escaneo del código QR
                escanearCodigoQR();
            }
        });
    }

    private void escanearCodigoQR() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Escanea un código QR"); // Mensaje en la pantalla de escaneo
        integrator.setBeepEnabled(true); // Activar sonido al escanear
        integrator.setOrientationLocked(false); // Permitir rotar la pantalla al escanear
        integrator.setRequestCode(REQUEST_CODE_SCAN); // Establecer el código de solicitud para obtener el resultado del escaneo
        integrator.initiateScan(); // Iniciar el escaneo
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Comprobar si el resultado proviene del escaneo de QR y si fue exitoso
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                // Obtener el texto del código QR escaneado
                String scannedCode = result.getContents();
                if (scannedCode != null && !scannedCode.isEmpty()) {
                    // Colocar el código escaneado en el EditText
                    editTextCodigoToken.setText(scannedCode);
                } else {
                    // Mensaje si no se pudo obtener el código escaneado
                    Toast.makeText(this, "No se pudo escanear el código QR", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * Agrega un nuevo token a la lista de tokens del profesor si el código es válido y el profesor no es nulo.
     * Si el código está vacío o el profesor es nulo, muestra un mensaje de error.
     */
    private void agregarToken() {
        // Obtener el código del token desde el EditText
        String codigo = editTextCodigoToken.getText().toString().trim();

        // Obtener el profesor desde MyApplicationData
        UserProfesor profesor = MyApplicationData.getInstance().getProfesor();
        Log.d("MainEditarNuevoToken", "Profesor: " + profesor);

        // Validar que el código no esté vacío y que el profesor no sea nulo
        if (!TextUtils.isEmpty(codigo) && profesor != null) {
            // Crear una instancia de UserToken con el código
            UserToken token = new UserToken(codigo, profesor.getRut(), "", "", profesor.getnombre_Apellido());
            String rutFirebase = profesor.getRut().replace(".", "").replace("-", "");

            // Agregar el nuevo token directamente a la colección "tokens" con el código como clave
            DatabaseReference profesorRef = FirebaseDatabase.getInstance().getReference("Profesores")
                    .child(rutFirebase);

            // Agregar el nuevo token a la lista de tokens del profesor
            profesorRef.child("tokens").child(codigo).setValue(token);

            // Actualizar el profesor en MyApplicationData
            profesor.agregarToken(token);
            MyApplicationData.getInstance().setProfesor(profesor);


        } else {
            // Mostrar un mensaje de error si el código está vacío o el profesor es nulo
            Toast.makeText(MainEditarNuevoToken.this, "Ingrese un código de token válido", Toast.LENGTH_SHORT).show();
        }
    }
}

