package com.UDEC.educaplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CalificacionesEstudiantesFragment extends Fragment {

    String cantidad, correctas, incorrectas, puntaje, nivel;
    ArrayList<Notas> listanotas;
    Connection con;
    RecyclerView recycler;
    String z = null;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String id_Usuario;
    private String mParam1;
    private String mParam2;

    public CalificacionesEstudiantesFragment() {

    }

    public static CalificacionesEstudiantesFragment newInstance(String param1, String param2) {
        CalificacionesEstudiantesFragment fragment = new CalificacionesEstudiantesFragment();
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
            id_Usuario = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calificaciones_estudiantes, container, false);
        listanotas = new ArrayList<>();
        recycler = view.findViewById(R.id.scrollcalificaciones);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        traercalificaciones();
        Adapter2 adapter2=new Adapter2(listanotas);
        recycler.setAdapter(adapter2);
        return view;
    }

    private void traercalificaciones() {
        con = conexionBD();
        try {
            String sql = "SELECT id_Prueba, Aciertos, Errores, Nota FROM Calificaciones WHERE id_Usuario = '"+id_Usuario+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                nivel = rs.getString(1);
                int auch = Integer.parseInt(nivel);
                auch = auch -1;
                nivel = Integer.toString(auch);
                correctas = rs.getString(2);
                incorrectas = rs.getString(3);
                puntaje = rs.getString(4);
                cantidad = correctas + incorrectas;
                Notas notas = new Notas(cantidad,correctas,incorrectas,puntaje,nivel);
                listanotas.add(notas);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
    }
}