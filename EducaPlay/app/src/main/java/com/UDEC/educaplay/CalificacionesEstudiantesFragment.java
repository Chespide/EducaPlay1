package com.UDEC.educaplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Connection;

public class CalificacionesEstudiantesFragment extends Fragment {

    TextView cantidad, correctas, incorrectas, puntaje;
    Connection con;
    String z = null;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String id_Usuario;
    private String mParam1;
    private String mParam2;

    public CalificacionesEstudiantesFragment() {
        // Required empty public constructor
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
        cantidad = view.findViewById(R.id.cantidadpreguntas);
        return view;
    }
}