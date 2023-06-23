package com.example.vettime2.ui.mascotas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentMascotasBinding;
import com.example.vettime2.ui.inicio.AdapterMascotasInicio;

public class MascotasFragment extends Fragment {

    private MascotasViewModel mViewModel;
    private FragmentMascotasBinding binding;

    public static MascotasFragment newInstance() {
        return new MascotasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMascotasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(MascotasViewModel.class);

        RecyclerView recyclerView = binding.recyclerViewM;
        GridLayoutManager grilla = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(grilla);

        mViewModel.getMascotas().observe(getViewLifecycleOwner(), mascotas -> {
            AdapterMascotasM adapter = new AdapterMascotasM(getContext(), mascotas, getLayoutInflater());
            recyclerView.setAdapter(adapter);
        });

        binding.btAgregarM.setOnClickListener(v -> {
            Navigation.findNavController(root).navigate(R.id.action_mascotasFragment_to_nuevaMascotaFragment);
        });

        binding.btCompartirM.setOnClickListener(v -> {
            Navigation.findNavController(root).navigate(R.id.action_mascotasFragment_to_compartidaFragment);
        });

        mViewModel.setmMascotas();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MascotasViewModel.class);
        // TODO: Use the ViewModel
    }

}