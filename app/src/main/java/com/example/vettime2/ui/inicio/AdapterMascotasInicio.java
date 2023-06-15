package com.example.vettime2.ui.inicio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vettime2.R;
import com.example.vettime2.modelos.Mascota;

import java.util.List;

public class AdapterMascotasInicio  extends RecyclerView.Adapter<AdapterMascotasInicio.ViewHolder> {

    private Context context;
    private List<Mascota> mascotas;
    private LayoutInflater layoutInflater;

    public AdapterMascotasInicio(Context context, List<Mascota> mascotas, LayoutInflater layoutInflater) {
        this.context = context;
        this.mascotas = mascotas;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root=layoutInflater.inflate(R.layout.mascotas_inicio,parent,false);
        return new  ViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nombre.setText(mascotas.get(position).getNombre()+" "+mascotas.get(position).getApellido());

        Glide.with(context)
                .load("https://http.cat/images/102.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        ImageView imagen;
        Button btnDetalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.tvNomMascota);
            imagen=itemView.findViewById(R.id.ivMascota);
            btnDetalle=itemView.findViewById(R.id.btDetalleMascotaInicio);

            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    Mascota laMascota = mascotas.get(getAdapterPosition());
                    bundle.putSerializable("mascota", laMascota);

                    Navigation.findNavController( (Activity)context,R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_dashboard_to_detalleMascotaFragment,bundle);
                }
            });
        }
    }

}
