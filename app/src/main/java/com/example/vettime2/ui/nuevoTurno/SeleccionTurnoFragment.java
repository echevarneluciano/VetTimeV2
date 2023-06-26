package com.example.vettime2.ui.nuevoTurno;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentSeleccionTurnoBinding;
import com.example.vettime2.modelos.Mascota;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SeleccionTurnoFragment extends Fragment {

    private SeleccionTurnoViewModel mViewModel;
    private FragmentSeleccionTurnoBinding binding;
    private String empleado;
    private String tarea;
    private LocalDate fecha;
    private LocalDateTime fechaHora;

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
            fecha = LocalDate.of(year, month+1, dayOfMonth);
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

        binding.btConfirmaConsulta.setOnClickListener(v -> {
            try {
                mViewModel.crearConsulta(empleado,binding.spHorario.getSelectedItem().toString(),binding.spMascota.getSelectedItem().toString());
            }catch (NullPointerException e){
                Toast.makeText(getContext(), "Seleccione dia, horario y mascota", Toast.LENGTH_SHORT).show();
            }
        });

        binding.spHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = parent.getItemAtPosition(position).toString();
                        LocalTime tiempo = LocalTime.parse(selectedItem);
                        int hora = tiempo.getHour();
                        int minutos = tiempo.getMinute();
                        fechaHora = fecha.atTime(hora,minutos);
                        mViewModel.compruebaLapso(selectedItem,fechaHora);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );

        mViewModel.getConsulta().observe(getViewLifecycleOwner(), consulta -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setTitle("Comprobante");
            builder.setView(R.layout.enviar_comprobante);
            builder.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Bundle bundle = new Bundle();
                    bundle.putString("origen","comprobante");
                    bundle.putSerializable("consulta",consulta);
                    Navigation.findNavController(root).navigate(R.id.action_seleccionTurnoFragment_to_archivosFragment,bundle);
                }
            });
            builder.setNegativeButton("Enviar en otro momento", null);
            AlertDialog dialog = builder.create();
            dialog.show();
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