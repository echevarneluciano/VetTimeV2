package com.example.vettime2.ui.turnos;

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

import com.example.vettime2.R;
import com.example.vettime2.databinding.FragmentTurnosBinding;
import com.example.vettime2.ui.inicio.AdapterMascotasInicio;

public class TurnosFragment extends Fragment {

    private FragmentTurnosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TurnosViewModel turnosViewModel =
                new ViewModelProvider(this).get(TurnosViewModel.class);

        binding = FragmentTurnosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerViewT;
        RecyclerView recyclerView2 = binding.rvHistorialT;

        GridLayoutManager grilla = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(grilla);

        GridLayoutManager grilla2 = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView2.setLayoutManager(grilla2);

        turnosViewModel.getConsultasPendientes().observe(getViewLifecycleOwner(), list -> {
            AdapterTurnos adapter = new AdapterTurnos(getContext(), list, getLayoutInflater());
            recyclerView.setAdapter(adapter);
        });

        turnosViewModel.getConsultasHistorial().observe(getViewLifecycleOwner(), list -> {
            Log.d("TurnosFragment", list.toString());
            AdapterTurnos adapter2 = new AdapterTurnos(getContext(), list, getLayoutInflater());
            recyclerView2.setAdapter(adapter2);
        });

        binding.btAgregarT.setOnClickListener(v -> {
            Navigation.findNavController(root).navigate(R.id.action_navigation_turnos_to_navigation_home);
        });

        turnosViewModel.setmConsultasHistorial();
        turnosViewModel.setmConsultasPendientes();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}