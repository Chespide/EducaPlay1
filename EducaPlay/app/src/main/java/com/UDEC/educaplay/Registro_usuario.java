package com.UDEC.educaplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class Registro_usuario extends AppCompatActivity {
    EditText nom, ape, correo, cod, contra;
    Button btnregistrarse;
    String pasarusu, rol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        nom = (EditText) findViewById(R.id.nombre_usuario);
        ape = (EditText) findViewById(R.id.apellido_usuario);
        correo = (EditText) findViewById(R.id.correo_usuario);
        cod = (EditText) findViewById(R.id.codigo_usuario);
        contra = (EditText) findViewById(R.id.pass_usuario);
        btnregistrarse = (Button) findViewById(R.id.btnregistrousuario);
        pasarusu = correo.getText().toString().trim();
        rol = "2";
        btnregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarusuario();
                Intent intent = new Intent(Registro_usuario.this, Iniciar_sesion_usuario.class);
                intent.putExtra("usu",pasarusu);
                startActivity(intent);
                finish();
            }
        });

    }
    public Connection conexionBD(){
        Connection conexion = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://gutgara.ddns.net;databaseName=EducaPlay;user=gutgara;password=VAuX2v_1xx0_T9w;");

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }
    public void agregarusuario(){
        try{
            PreparedStatement pst = conexionBD().prepareStatement("insert into Usuarios values(?,?,?,?,?,?)");
            pst.setString(1,rol);
            pst.setString(2,nom.getText().toString());
            pst.setString(3,ape.getText().toString());
            pst.setString(4,cod.getText().toString());
            pst.setString(5,correo.getText().toString());
            pst.setString(6,contra.getText().toString());
            pst.executeUpdate();
            Toast.makeText(getApplicationContext(),"REGISTRO AGREGADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
        }catch (SQLDataException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void registrarseusuario(View view){
        Intent inic = new Intent(this,Iniciar_sesion_usuario.class);
        startActivity(inic);
    }
}