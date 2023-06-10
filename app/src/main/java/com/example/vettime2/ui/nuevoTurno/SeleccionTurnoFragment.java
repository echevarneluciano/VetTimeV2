package com.example.vettime2.ui.nuevoTurno;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentSeleccionTurnoBinding;

public class SeleccionTurnoFragment extends Fragment {

    private SeleccionTurnoViewModel mViewModel;
    private FragmentSeleccionTurnoBinding binding;
    private String empleado;
    private String tarea;

    public static SeleccionTurnoFragment newInstance() {
        return new SeleccionTurnoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SeleccionTurnoViewModel.class);
        binding = FragmentSeleccionTurnoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        empleado = getArguments().getString("empleado");
        tarea = getArguments().getString("tarea");

        binding.dtCalendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            mViewModel.setHorarios(dayOfMonth, month+1, year, tarea, empleado);
        });

        mViewModel.getHorarios().observe(getViewLifecycleOwner(), horarios -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, horarios);
            binding.spHorario.setAdapter(adapter);
        });

        mViewModel.getMascotas().observe(getViewLifecycleOwner(), mascotas -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mascotas);
            binding.spMascota.setAdapter(adapter);
        });

        mViewModel.setMascotas();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SeleccionTurnoViewModel.class);
        // TODO: Use the ViewModel
    }

}