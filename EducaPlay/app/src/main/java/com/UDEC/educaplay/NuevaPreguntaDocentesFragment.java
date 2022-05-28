package com.UDEC.educaplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class NuevaPreguntaDocentesFragment extends Fragment {

    EditText nivelpregunta, pregunta, a, b, c, d;
    CheckBox a1,b1,c1,d1;
    Button agregarnuevapregunta;
    String verdadera;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NuevaPreguntaDocentesFragment() {

    }

    public static NuevaPreguntaDocentesFragment newInstance(String param1, String param2) {
        NuevaPreguntaDocentesFragment fragment = new NuevaPreguntaDocentesFragment();
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
        View view = inflater.inflate(R.layout.fragment_nueva_pregunta_docentes, container, false);
        nivelpregunta = view.findViewById(R.id.nivelnuevapregunta);
        pregunta = view.findViewById(R.id.nuevapregunta);
        a1 = view.findViewById(R.id.check_a);
        b1 = view.findViewById(R.id.check_b);
        c1 = view.findViewById(R.id.check_c);
        d1 = view.findViewById(R.id.check_d);

        a = view.findViewById(R.id.respuesta1);
        b = view.findViewById(R.id.respuesta2);
        c = view.findViewById(R.id.respuesta3);
        d = view.findViewById(R.id.respuesta4);
        agregarnuevapregunta = view.findViewById(R.id.crearpregunta);
        agregarnuevapregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar();
                nuevapregunta();
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
    public void nuevapregunta(){
        try{
            PreparedStatement pst = conexionBD().prepareStatement("insert into Preguntas values(?,?,?,?,?,?,?)");
            pst.setString(1,nivelpregunta.getText().toString());
            pst.setString(2,pregunta.getText().toString());
            pst.setString(3,a.getText().toString());
            pst.setString(4,b.getText().toString());
            pst.setString(5,c.getText().toString());
            pst.setString(6,d.getText().toString());
            pst.setString(7,verdadera);
            pst.executeUpdate();
            Toast.makeText(getContext(),"REGISTRO AGREGADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
        }catch (SQLDataException e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void validar(){
        if(a1.isChecked()){
            verdadera = "1";
        }
        if(b1.isChecked()){
            verdadera = "2";
        }
        if(c1.isChecked()){
            verdadera = "3";
        }
        if(d1.isChecked()){
            verdadera = "4";
        }

    }
}