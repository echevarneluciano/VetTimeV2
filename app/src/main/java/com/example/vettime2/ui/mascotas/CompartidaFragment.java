package com.example.vettime2.ui.mascotas;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentCompartidaBinding;
import com.example.vettime2.modelos.Mascota;

public class CompartidaFragment extends Fragment {

    private CompartidaViewModel mViewModel;
    private FragmentCompartidaBinding binding;
    private Mascota mascotaCompartida;

    public static CompartidaFragment newInstance() {
        return new CompartidaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CompartidaViewModel.class);

        binding = FragmentCompartidaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.getMascota().observe(getViewLifecycleOwner(), mascota -> {

            LayoutInflater inflater2 = LayoutInflater.from(getContext());
            View view = inflater2.inflate(R.layout.mascota_compartida, null);
            TextView mascotaNombre = view.findViewById(R.id.tvMascotaNombreCompartida);
            TextView mascotaApe = view.findViewById(R.id.tvMascotaApeCompartida);
            mascotaNombre.setText("Nombre: " + mascota.getNombre());
            mascotaApe.setText("Apellido: " + mascota.getApellido());

            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setTitle("Mascota compartida");
            builder.setView(view);
            builder.setPositiveButton("Es mi mascota", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    binding.btConfirmar.setEnabled(true);
                    mascotaCompartida = mascota;
                }
            });
            builder.setNegativeButton("No lo es. Buscar nuevamente", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        binding.btComprobar.setOnClickListener(v ->  {
            mViewModel.compruebaUid(binding.etUidEntrada.getText().toString());
        });

        binding.btConfirmar.setOnClickListener(v -> {
            mViewModel.agregarCompartida(mascotaCompartida);
        });

        mViewModel.getMascotaCompartida().observe(getViewLifecycleOwner(), mascota -> {
           Navigation.findNavController(root).navigate(R.id.action_compartidaFragment_to_mascotasFragment);
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CompartidaViewModel.class);
        // TODO: Use the ViewModel
    }

}