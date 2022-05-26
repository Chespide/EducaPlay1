package com.UDEC.educaplay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Iniciar_sesion_usuario extends AppCompatActivity {
    EditText usuario, contraseña;
    Button iniciar_sesion;
    Connection con;
    String nombreusuario, nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion_usuario);
        usuario = (EditText) findViewById(R.id.correo_usuario);
        contraseña = (EditText) findViewById(R.id.pass_usuario);
        iniciar_sesion = (Button) findViewById(R.id.button_iniciar_usuario);
        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new checkLogin().execute("");
                finish();
            }
        });
    }

    public class checkLogin extends AsyncTask<String, String, String> {

        String z = null;
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            con = conexionBD(ConnectionClass.rol.toString(),ConnectionClass.nom.toString(), ConnectionClass.ape.toString(), ConnectionClass.correo.toString(), ConnectionClass.correo.toString(), ConnectionClass.contra.toString());
            if (con == null) {
                Toast.makeText(Iniciar_sesion_usuario.this, "Revisa tu conexion", Toast.LENGTH_LONG).show();
            } else {
                try {

                    String sql = "SELECT * FROM Usuarios WHERE Correo = '" + usuario.getText() + "' AND Pass = '" + contraseña.getText() + "' ";
                    nombreusuario = usuario.getText().toString();
                    nom = "" + nombreusuario;
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        String id_Usuario = rs.getString(1);
                        String documentousuario = rs.getString(5);
                        Bundle bundle = new Bundle();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Iniciar_sesion_usuario.this, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show();
                            }
                        });
                        z = "Success";
                        Intent intent = new Intent(Iniciar_sesion_usuario.this, menus_de_estudiante.class);
                        bundle.putString("Documento", documentousuario);
                        bundle.putString("id",id_Usuario);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Iniciar_sesion_usuario.this, "Revisa tu usuario o contraseña", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Iniciar_sesion_usuario.this, Iniciar_sesion_usuario.class);
                                startActivity(intent);
                            }
                        });
                        usuario.setText("");
                        contraseña.setText("");
                    }

                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error :", e.getMessage());
                }
            }
            return z;
        }
    }
    @SuppressLint("NewApi")
    public Connection conexionBD(String rol,String nom, String ape, String corr, String usu, String cotra) {
        Connection conexion = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://gutgara.ddns.net;databaseName=EducaPlay;user=gutgara;password=VAuX2v_1xx0_T9w");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }
    public void nuevoestudiante (View view){
        Intent in = new Intent(this, Registro_usuario.class);
        startActivity(in);
    }

}