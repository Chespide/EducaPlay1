package com.UDEC.educaplay;

import android.database.Cursor;
import android.icu.lang.UCharacter;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InicioEstudiantesFragment extends Fragment {

    ArrayList<Contenido> listDatos;
    RecyclerView recycler;
    Connection conn;
    String titulo, Descripcion, Contenido, Nivel, id;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public InicioEstudiantesFragment() {

    }
    public static InicioEstudiantesFragment newInstance(String param1, String param2) {
        InicioEstudiantesFragment fragment = new InicioEstudiantesFragment();
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
        View view = inflater.inflate(R.layout.fragment_inicio_estudiantes, container, false);
        listDatos = new ArrayList<>();
        recycler = view.findViewById(R.id.scrollinicio);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        inicioestudiantes();
        Adapter1 adapter1=new Adapter1(listDatos);
        recycler.setAdapter(adapter1);
        adapter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.frame_layout_estudiantes, EntradaEstudiantesFragment.newInstance(listDatos.get(recycler.getChildAdapterPosition(view)).getTitulo(),listDatos.get(recycler.getChildAdapterPosition(view)).getContenido()));
                transaction.commit();
            }
        });

        return view;
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
    public  void inicioestudiantes(){
        conn = conexionBD();
        try {
            String sql = "SELECT id_Entrada,Titulo, Descripcion, Contenido, id_Nivel FROM Entradas";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                id = rs.getString(1);
                titulo = rs.getString(2);
                Descripcion = rs.getString(3);
                Contenido = rs.getString(4);
                Nivel = rs.getString(5);
                Contenido contenido =new Contenido(id,titulo,Descripcion,Contenido,Nivel);
                listDatos.add(contenido);
                Log.i("vista",String.valueOf(titulo));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}