package com.UDEC.educaplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class InicioEstudiantesFragment extends Fragment {

    ArrayList<String> listDatos;
    RecyclerView recycler;

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
        recycler = view.findViewById(R.id.scrollinicio);
        listDatos = new ArrayList<>();
        for (int i=0;i<50;i++){
            listDatos.add("Dato # "+i+" ");
        }
        Adapter1 adapter1=new Adapter1(listDatos);
        recycler.setAdapter(adapter1);


        return view;
    }
}