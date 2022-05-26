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

        Bundle bundle = getIntent().getExtras();
        String documentousuario = bundle.getString("Documento");
        String id_Usuario = bundle.getString("id");
        Bundle args = new Bundle();
        args.putString("Documento", documentousuario);
        args.putString("id", id_Usuario);

        replaceFragment(new InicioDocentesFragment(),args);

        binding.bottomNavigationViewDocentes.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.iniciodocentes:
                    replaceFragment(new InicioDocentesFragment(),args);
                    break;
                case R.id.pruebasdocentes:
                    replaceFragment(new PruebasDocentesFragment(),args);
                    break;
                case R.id.perfildocentes:
                    replaceFragment(new PerfilDocentesFragment(),args);
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment, Bundle bundle){

        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_docentes, fragment);
        fragmentTransaction.commit();

    }

}