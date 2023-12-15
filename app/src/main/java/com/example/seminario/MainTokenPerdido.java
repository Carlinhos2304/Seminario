package com.example.seminario;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainTokenPerdido extends AppCompatActivity {

    private Spinner spinnerPerdido;
    private EditText fechaperdida;
    private EditText mensajeperdida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.token_perdido);
        spinnerPerdido=findViewById(R.id.spinnerPerdido);
        fechaperdida=findViewById(R.id.fechaperdida);
        mensajeperdida=findViewById(R.id.editTextMensajePerdida);

}}
