package com.example.vettime2.ui.inicio;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentDetalleMascotaBinding;
import com.example.vettime2.modelos.Mascota;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalleMascotaFragment extends Fragment {

    private DetalleMascotaViewModel mViewModel;
    private FragmentDetalleMascotaBinding binding;
    private SimpleDateFormat formatoFechaSql= new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

    public static DetalleMascotaFragment newInstance() {
        return new DetalleMascotaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(DetalleMascotaViewModel.class);

        Bundle bundle = this.getArguments();
        Mascota mascota = (Mascota) bundle.getSerializable("mascota");

        mViewModel.getMascota().observe(getViewLifecycleOwner(), mascotaMod -> {
            cargaPerfil(mascotaMod);
        });

        binding = FragmentDetalleMascotaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btEditarM.setOnClickListener(v -> {
            editable();
        });

        binding.btGuardarM.setOnClickListener(v -> {
            Mascota mascotaEditada = new Mascota();
            mascotaEditada.setId(mascota.getId());
            mascotaEditada.setNombre(binding.etNombreMascota.getText().toString());
            mascotaEditada.setApellido(binding.etApellidoMascota.getText().toString());
            mascotaEditada.setPeso(Float.parseFloat(binding.etPesoMascota.getText().toString()));
            String fechaFormato = "";
            try {
                Date fecha = formatoFecha.parse(binding.etFechaMascota.getText().toString());
                fechaFormato = formatoFechaSql.format(fecha);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            mascotaEditada.setFechaNacimiento(fechaFormato);
            mViewModel.setMascota(mascotaEditada);
        });

        cargaPerfil(mascota);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleMascotaViewModel.class);
        // TODO: Use the ViewModel
    }

    public void cargaPerfil(Mascota mascota){
        Glide.with(this)
                .load("https://http.cat/images/301.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivMascotaPerfil);

        binding.etNombreMascota.setText(mascota.getNombre());
        binding.etNombreMascota.setEnabled(false);
        binding.etApellidoMascota.setText(mascota.getApellido());
        binding.etApellidoMascota.setEnabled(false);
        String fecha = "";
        try {
            Date fechaNacimiento = formatoFechaSql.parse(mascota.getFechaNacimiento());
            fecha = formatoFecha.format(fechaNacimiento);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        binding.etFechaMascota.setText(fecha);
        binding.etFechaMascota.setEnabled(false);
        binding.etPesoMascota.setText(String.valueOf(mascota.getPeso()));
        binding.etPesoMascota.setEnabled(false);
    }

    public void editable(){
        binding.etNombreMascota.setEnabled(true);
        binding.etApellidoMascota.setEnabled(true);
        binding.etFechaMascota.setEnabled(true);
        binding.etPesoMascota.setEnabled(true);

        EditText editTextFecha = binding.etFechaMascota;
        editTextFecha.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
        editTextFecha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String inputText = editTextFecha.getText().toString();

                if (inputText.length() != 10) {
                    editTextFecha.setError("Debe ingresar una fecha valida (DD-MM-YYYY)");
                } else {
                    editTextFecha.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Obtén el texto ingresado por el usuario
                String inputText = editTextFecha.getText().toString();

                try {
                    // Intenta analizar la fecha ingresada
                    Date fecha = formatoFecha.parse(inputText);

                    // La fecha es válida, puedes realizar cualquier acción adicional aquí
                } catch (ParseException e) {
                    // La fecha no es válida, muestra un mensaje de error o realiza una acción apropiada
                    editTextFecha.setError("Fecha inválida");
                }
            }
        });
    }
    }