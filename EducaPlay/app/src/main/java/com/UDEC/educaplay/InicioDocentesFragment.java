package com.UDEC.educaplay;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InicioDocentesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnnuevaentrada;
    private String mParam1;
    private String mParam2;

    public InicioDocentesFragment() {

    }

    public static InicioDocentesFragment newInstance(String param1, String param2) {
        InicioDocentesFragment fragment = new InicioDocentesFragment();
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
        View view = inflater.inflate(R.layout.fragment_inicio_docentes, container, false);
        btnnuevaentrada = (Button) view.findViewById(R.id.btnnuevaentrada);
        btnnuevaentrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NuevaEntradaDocentesFragment fragment = new NuevaEntradaDocentesFragment();
                //Bundle args = new Bundle();
                //args.putInt(NuevaEntradaDocentesFragment.);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout_docentes, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

}