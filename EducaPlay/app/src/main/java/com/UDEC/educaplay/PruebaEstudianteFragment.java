package com.UDEC.educaplay;

import android.annotation.SuppressLint;
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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class PruebaEstudianteFragment extends Fragment {
    Connection conn1;
    CheckBox tex1,tex2,tex3,tex4;
    TextView tex;
    String correcta, respuesta, verdadera, a,b,c,d,pregunta, aux1,aux2;
    ArrayList <Preguntas> listapreguntas;
    Button btnsiguiente;
    int i, aciertos,fallos;
    float puntajerespuesta;
    boolean cambio;
    int position;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PruebaEstudianteFragment() {

    }

    public static PruebaEstudianteFragment newInstance(String param1, String param2) {
        PruebaEstudianteFragment fragment = new PruebaEstudianteFragment();
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
        View view = inflater.inflate(R.layout.fragment_prueba_estudiante,container,false);
        listapreguntas = new ArrayList<>();
        tex = view.findViewById(R.id.texto_de_preguntas);
        tex1 = view.findViewById(R.id.respuestaA);
        tex2 = view.findViewById(R.id.respuestaB);
        tex3 = view.findViewById(R.id.respuestaC);
        tex4 = view.findViewById(R.id.respuestaD);
        btnsiguiente = view.findViewById(R.id.btn_siguiente_pregunta);
        btnsiguiente.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                if(position <= i-2){
                    position = position + 1;
                    tex.setText(listapreguntas.get(position).getTex());
                    tex1.setText("A: "+listapreguntas.get(position).getTex1());
                    tex2.setText("B: "+listapreguntas.get(position).getTex2());
                    tex3.setText("C: "+listapreguntas.get(position).getTex3());
                    tex4.setText("D: "+listapreguntas.get(position).getTex4());
                    respuesta = validar();
                    verdadera = listapreguntas.get(position-1).getTex5();
                    if(Objects.equals(respuesta, verdadera)){
                        aciertos = aciertos + 1;
                        puntajerespuesta = puntajerespuesta + 100;
                    }else{
                        fallos = fallos + 1;
                    }
                }
                else{
                    cargarrespuesta(puntajerespuesta,aciertos,fallos,position+1);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);
                    transaction.replace(R.id.frame_layout_estudiantes, PruebasEstudiantesFragment.newInstance("",""));
                    transaction.commit();
                }
            }
        });
        inicioprueba();

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
    @SuppressLint("SetTextI18n")
    public void inicioprueba(){

        conn1 = conexionBD();
        try {
            cambio = false;
            String sql = "SELECT Pregunta, A, B, C, D, Verdadera FROM Preguntas WHERE id_Nivel = 1";
            Statement stmt = conn1.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()|| i==10){
                pregunta = rs.getString(1);
                a = rs.getString(2);
                b = rs.getString(3);
                c = rs.getString(4);
                d = rs.getString(5);
                correcta = rs.getString(6);
                Preguntas preguntas = new Preguntas(pregunta,a,b,c,d,correcta);
                listapreguntas.add(preguntas);
                i=i+1;
            }
            tex.setText(listapreguntas.get(position).getTex());
            tex1.setText("A: "+listapreguntas.get(position).getTex1());
            tex2.setText("B: "+listapreguntas.get(position).getTex2());
            tex3.setText("C: "+listapreguntas.get(position).getTex3());
            tex4.setText("D: "+listapreguntas.get(position).getTex4());
            respuesta = validar();
            if (!Objects.equals(respuesta, listapreguntas.get(position).getTex5())) {
                fallos = fallos + 1;
            } else {
                aciertos = aciertos + 1;
                puntajerespuesta = puntajerespuesta + 100;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void cargarrespuesta(float puntaje, int aciertos, int fallos, int cant){
        puntaje = puntaje / cant;
        conn1 = conexionBD();
        try {
            String sql = "SELECT id_Prueba FROM Calificaciones WHERE id_Usuario = '"+mParam2+"'";
            Statement stmt = conn1.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            aux1 = rs.getString(1);
            aux2 = mParam2;
            Log.i("id_prueba obtenido:",String.valueOf(aux1));
            Log.i("id_prueba generado:",String.valueOf(aux2));

            if(Objects.equals(aux1, aux2)){
                try{
                    PreparedStatement pst = conexionBD().prepareStatement("UPDATE table Calificaciones SET Aciertos = '"+aciertos+"',Errores = '"+fallos+"',Nota = '"+puntaje+"' WHERE id_Usuario = '"+mParam2+"'");
                    pst.setInt(1,Integer.parseInt(mParam2));
                    pst.setInt(2,Integer.parseInt(mParam1)+1);
                    pst.setInt(3,aciertos);
                    pst.setInt(4,fallos);
                    pst.setFloat(5,puntaje);
                    pst.executeUpdate();
                    Toast.makeText(getContext(),"NOTA ACTUALIZADA CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                }catch (SQLDataException e){
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }else {
                try{
                    PreparedStatement pst = conexionBD().prepareStatement("insert into Calificaciones values(?,?,?,?,?)");
                    pst.setInt(1,Integer.parseInt(mParam2));
                    pst.setInt(2,Integer.parseInt(mParam1)+1);
                    pst.setInt(3,aciertos);
                    pst.setInt(4,fallos);
                    pst.setFloat(5,puntaje);
                    pst.executeUpdate();
                    Toast.makeText(getContext(),"NOTA AGREGADA CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                }catch (SQLDataException e){
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String validar(){
        if(tex1.isChecked()){
            return "1";
        }
        if(tex2.isChecked()){
            return  "2";
        }
        if(tex3.isChecked()){
            return "3";
        }
        if(tex4.isChecked()){
            return "4";
        }
        return null;
    }
}