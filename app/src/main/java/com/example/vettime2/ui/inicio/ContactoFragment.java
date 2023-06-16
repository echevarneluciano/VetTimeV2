package com.example.vettime2.ui.inicio;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentContactoBinding;

public class ContactoFragment extends Fragment {

    private ContactoViewModel mViewModel;
    private FragmentContactoBinding binding;

    public static ContactoFragment newInstance() {
        return new ContactoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentContactoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);

        Glide.with(this)
                .load("https://http.cat/images/301.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivContacto);

        mViewModel.getSucursal().observe(getViewLifecycleOwner(), sucursal -> {
            binding.etNombreVete.setText(sucursal.getNombre());
            binding.etDireccionV.setText(sucursal.getDireccion());
            binding.ettelV.setText(sucursal.getTelefono());
            binding.etHorV.setText(sucursal.getHorario());
            binding.etRedesD.setText(sucursal.getRedSocial());
        });

        mViewModel.setSucursal();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);
        // TODO: Use the ViewModel
    }

}