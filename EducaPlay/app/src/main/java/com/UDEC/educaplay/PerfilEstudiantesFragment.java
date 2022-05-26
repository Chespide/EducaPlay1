package com.UDEC.educaplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

public class PerfilEstudiantesFragment extends Fragment {

    TextView nom, correo, cod;
    Connection con;
    String z = null;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String Documento;
    private String mParam1;
    private String mParam2;

    public PerfilEstudiantesFragment() {

    }

    public static PerfilEstudiantesFragment newInstance(String param1, String param2) {
        PerfilEstudiantesFragment fragment = new PerfilEstudiantesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Documento =  getArguments().getString("Documento");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_estudiantes, container, false);
        nom = view.findViewById(R.id.idnombreusuario);
        correo = view.findViewById(R.id.idcorreousuario);
        cod = view.findViewById(R.id.idcodigousuario);
        new  checkLogin().execute("");

        return view;
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
            con = conexionBD(ConnectionClass.rol.toString(), ConnectionClass.nom.toString(), ConnectionClass.ape.toString(), ConnectionClass.correo.toString(), ConnectionClass.correo.toString(), ConnectionClass.contra.toString());
            if (con == null) {
                Toast.makeText(getContext(), "Revisa tu conexion", Toast.LENGTH_LONG).show();
            } else {
                try {

                    String sql = "SELECT Nombre, Documento, Correo FROM Usuarios WHERE Documento = '" + Integer.parseInt(Documento) + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        String nomusuario = nom.getText().toString();
                        nomusuario = nomusuario + " " + rs.getString(1);
                        nom.setText(nomusuario);
                        Log.i("Valor2:", String.valueOf(nomusuario));

                        String correousuario = correo.getText().toString();
                        correousuario = correousuario + " " + rs.getString(3);
                        correo.setText(correousuario);

                        String codigousuario = cod.getText().toString();
                        codigousuario = codigousuario + " " + rs.getString(2);
                        cod.setText(codigousuario);
                    }

                } catch (Exception e) {
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
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }








































    /*public Connection conexionBD(){
        Connection conexion = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://gutgara.ddns.net;databaseName=EducaPlay;user=gutgara;password=VAuX2v_1xx0_T9w;");
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }
    public  void mostrardatosusuario(){
        try {
            PreparedStatement pst = conexionBD().prepareStatement("SELECT Nombre, Documento, Correo FROM Usuarios WHERE Documento = '"+Integer.parseInt(Documento)+"'");
            ResultSet rs = pst.executeQuery();
            Log.i("Valu1:", String.valueOf(Documento));
            String ver = rs.getString(1);
            Log.i("Value:", String.valueOf(ver));


            String nomusuario = nom.getText().toString();
            nomusuario = nomusuario + " " + rs.getString(1);
            nom.setText(nomusuario);
            Log.i("Valor2:",String.valueOf(nomusuario));

            String correousuario = correo.getText().toString();
            correousuario = correousuario + " " + rs.getString(2);
            correo.setText(correousuario);

            String codigousuario = cod.getText().toString();
            codigousuario = codigousuario + " " + rs.getString(3);
            cod.setText(codigousuario);


        }catch (SQLException e){
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }*/
}