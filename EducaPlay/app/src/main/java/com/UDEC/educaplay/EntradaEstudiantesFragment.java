package com.UDEC.educaplay;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EntradaEstudiantesFragment extends Fragment {
    Connection con;
    TextView title, cont;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public EntradaEstudiantesFragment() {

    }

    public static EntradaEstudiantesFragment newInstance(String param1, String param2) {
        EntradaEstudiantesFragment fragment = new EntradaEstudiantesFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entrada_estudiantes, container, false);
        title = view.findViewById(R.id.titulo_contenido_estudiante);
        cont = view.findViewById(R.id.Texto_contenido_estudiante);
        title.setText(mParam1);
        cont.setText(mParam2);

        return view;
    }
    /*public class checkLogin extends AsyncTask<String, String, String> {

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
            con = conexionBD();
            if (con == null) {
                Toast.makeText(getContext(), "Revisa tu conexion", Toast.LENGTH_LONG).show();
            } else {
                try {

                    String sql = "SELECT Titulo, Contenido FROM Entradas WHERE id_Entrada = '" + Integer.parseInt(mParam1) +"'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        String nomusuario = title.getText().toString();
                        nomusuario = nomusuario + " " + rs.getString(1);
                        title.setText(nomusuario);

                        String correousuario = cont.getText().toString();
                        correousuario = correousuario + " " + rs.getString(3);
                        cont.setText(correousuario);

                        Log.i("Valores:", String.valueOf(title));
                        Log.i("Valores:", String.valueOf(cont));
                    }

                } catch (Exception e) {
                    Log.e("SQL Error :", e.getMessage());
                }
            }
            return z;
        }
    }

    @SuppressLint("NewApi")
    public Connection conexionBD() {
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
    }*/
}