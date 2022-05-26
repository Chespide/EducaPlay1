package com.UDEC.educaplay;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
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

    EditText titulo, descripcion, texto;
    Button btnnuevaentrada;
    String nivel;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private String id_Usuario;

    public NuevaEntradaDocentesFragment() {

    }

    public static NuevaEntradaDocentesFragment newInstance(String param1, String param2) {
        NuevaEntradaDocentesFragment fragment = new NuevaEntradaDocentesFragment();
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
        View view = inflater.inflate(R.layout.fragment_inicio_docentes, container, false);
        titulo = view.findViewById(R.id.entradatitulo);
        descripcion = view.findViewById(R.id.entradadescripcion);
        texto = view.findViewById(R.id.entradatexto);
        nivel = "1";
        /*btnnuevaentrada = (Button) view.findViewById(R.id.btncrearentrada);
        btnnuevaentrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaentrada();
                Intent btnnuevaentrada = new Intent(getActivity(), InicioDocentesFragment.class);
                startActivity(btnnuevaentrada);
            }
        });*/


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
            PreparedStatement pst = conexionBD().prepareStatement("insert into Entradas values(?,?,?,?,?,?)");
            pst.setString(1,nivel);
            pst.setString(2,id_Usuario);
            pst.setString(3,titulo.getText().toString());
            pst.setString(4,descripcion.getText().toString());
            pst.setString(5,texto.getText().toString());
            pst.executeUpdate();
            Toast.makeText(getContext(),"REGISTRO AGREGADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
        }catch (SQLDataException e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}