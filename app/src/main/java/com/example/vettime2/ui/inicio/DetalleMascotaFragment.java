package com.example.vettime2.ui.inicio;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentDetalleMascotaBinding;
import com.example.vettime2.modelos.Mascota;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
                Log.e("Error", e.getMessage());
            }
            mascotaEditada.setFechaNacimiento(fechaFormato);
            mViewModel.setMascota(mascotaEditada);
            Navigation.findNavController(root).navigate(R.id.action_detalleMascotaFragment_to_navigation_dashboard);
        });

        binding.ivMascotaPerfil.setOnClickListener(v -> {
            bundle.putSerializable("mascota", mascota);
            bundle.putString("origen", "mascota");
            Navigation.findNavController(v).navigate(R.id.action_detalleMascotaFragment_to_archivosFragment,bundle);
        });

        binding.butEliminarM.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setTitle("Eliminar mascota");
            builder.setView(R.layout.desea_eliminar);
            builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   mViewModel.eliminarMascota(mascota);
                }
            });
            builder.setNegativeButton("Cancelar", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        mViewModel.getMascotaEliminada().observe(getViewLifecycleOwner(), mascotaEliminada -> {
           Navigation.findNavController(binding.getRoot()).navigate(R.id.action_detalleMascotaFragment_to_navigation_dashboard);
        });

        binding.etFechaMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(requireContext());
                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       binding.etFechaMascota.setText((dayOfMonth)+"-"+(month+1)+"-"+year);
                    }
                });
                datePicker.show();
            }
        });

        binding.etFechaMascota.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DatePickerDialog datePicker = new DatePickerDialog(requireContext());
                    datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            binding.etFechaMascota.setText((dayOfMonth)+"-"+(month+1)+"-"+year);
                        }
                    });
                    datePicker.show();
                }
            }
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
                .load("http://192.168.15.7:5111"+mascota.getFoto())
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
            Log.d("Error", e.getMessage());
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
    }
    }