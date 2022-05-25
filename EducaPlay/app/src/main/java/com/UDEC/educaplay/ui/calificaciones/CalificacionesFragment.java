package com.UDEC.educaplay.ui.calificaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.UDEC.educaplay.databinding.FragmentCalificacionesBinding;

public class CalificacionesFragment extends Fragment {

    private FragmentCalificacionesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalificacionesViewModel calificacionesViewModel =
                new ViewModelProvider(this).get(CalificacionesViewModel.class);

        binding = FragmentCalificacionesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}