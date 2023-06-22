package com.example.vettime2.ui.mascotas;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
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

        binding.etFechaMascotaN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(requireContext());
                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        binding.etFechaMascotaN.setText((dayOfMonth)+"-"+(month+1)+"-"+year);
                    }
                });
                datePicker.show();
            }
        });

        binding.etFechaMascotaN.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DatePickerDialog datePicker = new DatePickerDialog(requireContext());
                    datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            binding.etFechaMascotaN.setText((dayOfMonth)+"-"+(month+1)+"-"+year);
                        }
                    });
                    datePicker.show();
                }
            }
        });

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
            Navigation.findNavController(root).navigate(R.id.action_nuevaMascotaFragment_to_mascotasFragment);
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