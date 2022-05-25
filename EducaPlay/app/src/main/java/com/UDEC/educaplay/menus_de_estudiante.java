package com.UDEC.educaplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.UDEC.educaplay.databinding.ActivityMenusDeEstudianteBinding;

public class menus_de_estudiante extends AppCompatActivity {

    ActivityMenusDeEstudianteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenusDeEstudianteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new InicioEstudiantesFragment());

        binding.bottomNavigationViewEstudiantes.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.inicioestudiantes:
                    replaceFragment(new InicioEstudiantesFragment());
                    break;
                case R.id.pruebasestudiantes:
                    replaceFragment(new PruebasEstudiantesFragment());
                    break;
                case R.id.calificacionesestudiantes:
                    replaceFragment(new CalificacionesEstudiantesFragment());
                    break;
                case R.id.perfilestudiantes:
                    replaceFragment(new PerfilEstudiantesFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_estudiantes, fragment);
        fragmentTransaction.commit();

    }
}