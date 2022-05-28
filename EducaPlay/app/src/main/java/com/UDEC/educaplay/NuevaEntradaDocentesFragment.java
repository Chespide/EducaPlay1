package com.UDEC.educaplay;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class NuevaEntradaDocentesFragment extends Fragment {

    EditText titulo, descripcion, texto, nivel;
    Button btnnuevaentrada;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NuevaEntradaDocentesFragment() {

    }

    public static NuevaEntradaDocentesFragment newInstance(String param1, String param2) {
        NuevaEntradaDocentesFragment fragment = new NuevaEntradaDocentesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_nueva_entrada_docentes, container, false);
        titulo = view.findViewById(R.id.entradatitulo);
        descripcion = view.findViewById(R.id.entradadescripcion);
        texto = view.findViewById(R.id.entradatexto);
        nivel = view.findViewById(R.id.nivelnuevaentrada);
        btnnuevaentrada = view.findViewById(R.id.btncrearentrada);
        btnnuevaentrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaentrada();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.frame_layout_docentes, InicioDocentesFragment.newInstance("",""));
                transaction.commit();

            }
        });

        return view;
    }
    public Connection conexionBD(){
        Connection conexion = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://gutgara.ddns.net;databaseName=EducaPlay;user=gutgara;password=VAuX2v_1xx0_T9w;");

        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }
    public void nuevaentrada(){
        try{
            PreparedStatement pst = conexionBD().prepareStatement("insert into Entradas values(?,?,?,?)");
            pst.setString(1,nivel.getText().toString());
            pst.setString(2,titulo.getText().toString());
            pst.setString(3,texto.getText().toString());
            pst.setString(4,descripcion.getText().toString());
            pst.executeUpdate();
            Toast.makeText(getContext(),"REGISTRO AGREGADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
        }catch (SQLDataException e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}