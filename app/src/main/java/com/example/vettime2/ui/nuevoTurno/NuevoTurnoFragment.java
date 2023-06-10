package com.example.vettime2.ui.nuevoTurno;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

        binding.btConfirma.setOnClickListener(v -> {

        });

    nuevoTurnoViewModel.getTareas().observe(getViewLifecycleOwner(), tareas -> {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tareas);
        binding.spTipoConsulta.setAdapter(adapter);
    });

    nuevoTurnoViewModel.getEmpleados().observe(getViewLifecycleOwner(), empleados -> {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, empleados);
        binding.spEmpleado.setAdapter(adapter);
    });

    binding.spTipoConsulta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            nuevoTurnoViewModel.setmEmpleados(item);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

    binding.btConfirma.setOnClickListener(v -> {
        nuevoTurnoViewModel.setConfirmado(binding.spTipoConsulta.getSelectedItem().toString(),binding.spEmpleado.getSelectedItem().toString(),this.getActivity());
    });

    nuevoTurnoViewModel.setmTareas();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}