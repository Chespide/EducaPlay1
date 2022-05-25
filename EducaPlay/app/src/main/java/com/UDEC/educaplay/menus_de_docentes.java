package com.UDEC.educaplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.UDEC.educaplay.databinding.ActivityMenusDeDocentesBinding;

public class menus_de_docentes extends AppCompatActivity {

    ActivityMenusDeDocentesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenusDeDocentesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new InicioDocentesFragment());

        binding.bottomNavigationViewDocentes.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.iniciodocentes:
                    replaceFragment(new InicioDocentesFragment());
                    break;
                case R.id.pruebasdocentes:
                    replaceFragment(new PruebasDocentesFragment());
                    break;
                case R.id.perfildocentes:
                    replaceFragment(new PerfilDocentesFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_docentes, fragment);
        fragmentTransaction.commit();

    }

}