package com.example.vettime2.ui.inicio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InicioViewModel inicioViewModel =
                new ViewModelProvider(this).get(InicioViewModel.class);

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Glide.with(this)
                .load("https://http.cat/images/207.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivUsuario);

        RecyclerView recyclerView = binding.rvMascotasInicio;
        GridLayoutManager grilla = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(grilla);

        inicioViewModel.getMascotas().observe(getViewLifecycleOwner(), mascotas -> {
            AdapterMascotasInicio adapter = new AdapterMascotasInicio(getContext(), mascotas, getLayoutInflater());
            recyclerView.setAdapter(adapter);
        });

        binding.btNuevaMascota.setOnClickListener(v -> {
            Navigation.findNavController(root).navigate(R.id.action_navigation_dashboard_to_nuevaMascotaFragment);
        });

        inicioViewModel.setmMascotas();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}