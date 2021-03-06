package com.UDEC.educaplay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
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

public class Iniciar_docentes extends AppCompatActivity {
    EditText usuario, contrasenia;
    Button iniciar_sesion;
    Connection con;
    String nombreusuario, nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_docentes);
        usuario = findViewById(R.id.usuario_docente);
        contrasenia = findViewById(R.id.pass_docente);
        iniciar_sesion = findViewById(R.id.btniniciardocente);
        iniciar_sesion.setOnClickListener(view -> {
            new checkLogin().execute("");
            finish();
        });
    }
    @SuppressLint("StaticFieldLeak")
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
            con = conexionBD(ConnectionClass.rol, ConnectionClass.nom, ConnectionClass.ape, ConnectionClass.correo, ConnectionClass.correo, ConnectionClass.contra);
            if (con == null)
                Toast.makeText(Iniciar_docentes.this, "Revisa tu conexion", Toast.LENGTH_LONG).show();
            else {
                try {

                    String sql = "SELECT * FROM Usuarios WHERE Documento = '" + usuario.getText() + "' AND Pass = '" + contrasenia.getText() + "' ";
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
                                Toast.makeText(Iniciar_docentes.this, "Inicio de sesi??n exitoso", Toast.LENGTH_LONG).show();
                            }
                        });
                        z = "Success";
                        Intent intent = new Intent(Iniciar_docentes.this, menus_de_docentes.class);
                        bundle.putString("Documento", documentousuario);
                        bundle.putString("id",id_Usuario);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Iniciar_docentes.this, "Revisa tu usuario o contrase??a", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Iniciar_docentes.this, Iniciar_docentes.class);
                                startActivity(intent);
                            }
                        });
                        usuario.setText("");
                        contrasenia.setText("");
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
    public void nuevodocente (View view){
        Intent in = new Intent(this, Registro_docentes.class);
        startActivity(in);
    }
}