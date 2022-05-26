package com.UDEC.educaplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.UDEC.educaplay.databinding.ActivityMenusDeEstudianteBinding;

public class menus_de_estudiante extends AppCompatActivity {

    ActivityMenusDeEstudianteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenusDeEstudianteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        String documentousuario = bundle.getString("Documento");
        String id_Usuario = bundle.getString("id");
        Bundle args = new Bundle();
        args.putString("Documento", documentousuario);
        args.putString("id", id_Usuario);

        replaceFragment(new InicioEstudiantesFragment(), args);

        binding.bottomNavigationViewEstudiantes.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.inicioestudiantes:
                    replaceFragment(new InicioEstudiantesFragment(), args);
                    break;
                case R.id.pruebasestudiantes:
                    replaceFragment(new PruebasEstudiantesFragment(), args);
                    break;
                case R.id.calificacionesestudiantes:
                    replaceFragment(new CalificacionesEstudiantesFragment(),args);
                    break;
                case R.id.perfilestudiantes:
                    replaceFragment(new PerfilEstudiantesFragment(),args);
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment, Bundle bundle){

        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_estudiantes, fragment);
        fragmentTransaction.commit();

    }
}