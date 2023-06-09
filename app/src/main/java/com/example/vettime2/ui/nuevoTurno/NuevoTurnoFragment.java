package com.example.vettime2.ui.nuevoTurno;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vettime2.databinding.FragmentNuevoturnoBinding;

public class NuevoTurnoFragment extends Fragment {

    private FragmentNuevoturnoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NuevoTurnoViewModel nuevoTurnoViewModel =
                new ViewModelProvider(this).get(NuevoTurnoViewModel.class);

        binding = FragmentNuevoturnoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nuevoTurnoViewModel.getTareas().observe(getViewLifecycleOwner(), tareas -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tareas);
            binding.spTarea.setAdapter(adapter);
        });

        nuevoTurnoViewModel.getMascotas().observe(getViewLifecycleOwner(), mascotas -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mascotas);
            binding.spMascota.setAdapter(adapter);
        });

        nuevoTurnoViewModel.getHorarios().observe(getViewLifecycleOwner(), horarios -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, horarios);
            binding.spHorario.setAdapter(adapter);
        });

        binding.dtCalendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            nuevoTurnoViewModel.setHorarios(dayOfMonth, month+1, year,binding.spTarea.getSelectedItem().toString());
        });

        binding.btConfirma.setOnClickListener(v -> {
            nuevoTurnoViewModel.crearConsulta(binding.spTarea.getSelectedItem().toString(),
                    binding.spHorario.getSelectedItem().toString(), binding.spMascota.getSelectedItem().toString());
        });

        nuevoTurnoViewModel.getReset().observe(getViewLifecycleOwner(), reset -> {
            resetCampos();
        });

        nuevoTurnoViewModel.setTareas();
        nuevoTurnoViewModel.setMascotas();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void resetCampos(){
        binding.spTarea.setSelection(0);
        binding.spHorario.setSelection(0);
        binding.spMascota.setSelection(0);
        binding.dtCalendario.setDate(System.currentTimeMillis());
    }
}