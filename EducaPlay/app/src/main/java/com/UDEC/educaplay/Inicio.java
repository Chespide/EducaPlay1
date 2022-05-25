package com.UDEC.educaplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }
    public void estudiante(View view){
        Intent regis = new Intent(this, Iniciar_sesion_usuario.class);
        startActivity(regis);
    }
    public void docente(View view){
        Intent regis = new Intent(this, Iniciar_docentes.class);
        startActivity(regis);
    }
}