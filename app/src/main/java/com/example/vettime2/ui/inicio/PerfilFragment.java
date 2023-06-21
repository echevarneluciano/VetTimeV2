package com.example.vettime2.ui.inicio;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentPerfilBinding;
import com.example.vettime2.modelos.Cliente;
import com.example.vettime2.modelos.Mascota;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private FragmentPerfilBinding binding;
    private Cliente clienteMod;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        mViewModel.getCliente().observe(getViewLifecycleOwner(), cliente -> {
            clienteMod = cliente;
            cargarPerfil(cliente);
        });

        binding.btEditarU.setOnClickListener(v -> {
           editable();
        });

        binding.btGuardarU.setOnClickListener(v -> {
           clienteMod.setNombre(binding.etNomU.getText().toString());
           clienteMod.setApellido(binding.etApeU.getText().toString());
           clienteMod.setDireccion(binding.etDireU.getText().toString());
           clienteMod.setMail(binding.etMailU.getText().toString());
           clienteMod.setTelefono(binding.etTeleU.getText().toString());
           mViewModel.modCliente(clienteMod);
        });

        binding.ivPerfilU.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            Mascota mascota = new Mascota();
            bundle.putSerializable("mascota", mascota);
            Navigation.findNavController(v).navigate(R.id.action_perfilFragment_to_archivosFragment, bundle);
        });

        mViewModel.setCliente();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

    public void cargarPerfil(Cliente cliente) {
        Glide.with(this)
                .load("http://192.168.15.7:5111"+cliente.getFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivPerfilU);
        binding.etNomU.setText(cliente.getNombre());
        binding.etNomU.setEnabled(false);
        binding.etApeU.setText(cliente.getApellido());
        binding.etApeU.setEnabled(false);
        binding.etDireU.setText(cliente.getDireccion());
        binding.etDireU.setEnabled(false);
        binding.etMailU.setText(cliente.getMail());
        binding.etMailU.setEnabled(false);
        binding.etTeleU.setText(cliente.getTelefono());
        binding.etTeleU.setEnabled(false);

    }

    public void editable(){
        binding.etNomU.setEnabled(true);
        binding.etApeU.setEnabled(true);
        binding.etDireU.setEnabled(true);
        binding.etMailU.setEnabled(true);
        binding.etTeleU.setEnabled(true);
    }

}