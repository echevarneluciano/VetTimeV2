package com.example.vettime2.ui.mascotas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentNuevaMascotaBinding;
import com.example.vettime2.modelos.Mascota;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NuevaMascotaFragment extends Fragment {

    private NuevaMascotaViewModel mViewModel;
    private FragmentNuevaMascotaBinding binding;
    private SimpleDateFormat formatoFechaSql= new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

    public static NuevaMascotaFragment newInstance() {
        return new NuevaMascotaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNuevaMascotaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(NuevaMascotaViewModel.class);

        binding.btGuardarMN.setOnClickListener(v -> {
            Mascota mascota = new Mascota();
            mascota.setNombre(binding.etNombreMascotaN.getText().toString());
            mascota.setActivo(1);
            mascota.setApellido(binding.etApellidoMascotaN.getText().toString());
            String fechaFormato = "";
            try {
                Date fecha = formatoFecha.parse(binding.etFechaMascotaN.getText().toString());
                fechaFormato = formatoFechaSql.format(fecha);
            } catch (ParseException e) {
                Toast.makeText(getContext(), "Error en formato, verifique fecha (dd-MM-yyyy)", Toast.LENGTH_SHORT).show();
                throw new RuntimeException(e);
            }
            mascota.setFechaNacimiento(fechaFormato);
            mViewModel.setmMascota(mascota);
        });

        mViewModel.getMascota().observe(getViewLifecycleOwner(), mascota -> {
           binding.etNombreMascotaN.setEnabled(false);
           binding.etApellidoMascotaN.setEnabled(false);
           binding.etFechaMascotaN.setEnabled(false);
           binding.btGuardarMN.setEnabled(false);
           binding.etPesoMascotaN.setEnabled(false);
        });

        Glide.with(this)
                .load("https://http.cat/images/301.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivMascotaPerfilN);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NuevaMascotaViewModel.class);
        // TODO: Use the ViewModel
    }

}