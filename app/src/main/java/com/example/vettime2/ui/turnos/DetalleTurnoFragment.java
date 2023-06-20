package com.example.vettime2.ui.turnos;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentDetalleTurnoBinding;
import com.example.vettime2.modelos.Consulta;

public class DetalleTurnoFragment extends Fragment {

    private DetalleTurnoViewModel mViewModel;
    private FragmentDetalleTurnoBinding binding;
    private Consulta consulta;

    public static DetalleTurnoFragment newInstance() {
        return new DetalleTurnoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleTurnoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle bundle = this.getArguments();
        consulta =(Consulta) bundle.getSerializable("consulta");

        binding.tvDetalleDetalle.setText(consulta.getDetalle());
        binding.tvFechaIniDetalle.setText(consulta.getTiempoInicio());
        binding.tvFechaFinDetalle.setText(consulta.getTiempoFin());
        binding.tvProfesionalDetalle.setText(consulta.getEmpleado().getNombre());
        binding.tvTurnoNomDetalle.setText(consulta.getCliente_mascota().getMascota().getNombre());

        mViewModel = new ViewModelProvider(this).get(DetalleTurnoViewModel.class);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleTurnoViewModel.class);
        // TODO: Use the ViewModel
    }

}