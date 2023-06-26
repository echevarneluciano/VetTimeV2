package com.example.vettime2.ui.turnos;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentDetalleTurnoBinding;
import com.example.vettime2.modelos.Consulta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalleTurnoFragment extends Fragment {

    private DetalleTurnoViewModel mViewModel;
    private FragmentDetalleTurnoBinding binding;
    private Consulta consulta;
    private SimpleDateFormat formaoSlida = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static DetalleTurnoFragment newInstance() {
        return new DetalleTurnoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DetalleTurnoViewModel.class);
        binding = FragmentDetalleTurnoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle bundle = this.getArguments();
        consulta =(Consulta) bundle.getSerializable("consulta");

        binding.tvDetalleDetalle.setText(consulta.getDetalle());

        try {
            Date entradaInicio = formatoEntrada.parse(consulta.getTiempoInicio());
            Date entradaFin = formatoEntrada.parse(consulta.getTiempoFin());
            String salidaInicio = formaoSlida.format(entradaInicio);
            String salidaFin = formaoSlida.format(entradaFin);
            binding.tvFechaIniDetalle.setText(salidaInicio);
            binding.tvFechaFinDetalle.setText(salidaFin);
        }catch (ParseException e) {
            Log.d("Error", e.getMessage());
        }

        binding.tvProfesionalDetalle.setText(consulta.getEmpleado().getNombre());
        binding.tvTurnoNomDetalle.setText(consulta.getCliente_mascota().getMascota().getNombre());

        mViewModel.getEstado().observe(getViewLifecycleOwner(), estado -> {
           binding.tvEstadoDetalle.setText(estado);
        });

        mViewModel.setmEstado(consulta.getEstado());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleTurnoViewModel.class);
        // TODO: Use the ViewModel
    }

}