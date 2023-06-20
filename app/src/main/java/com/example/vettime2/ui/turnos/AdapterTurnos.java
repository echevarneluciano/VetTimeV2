package com.example.vettime2.ui.turnos;

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
import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.modelos.Mascota;

import java.util.List;

public class AdapterTurnos extends RecyclerView.Adapter<AdapterTurnos.ViewHolder> {

    private Context context;
    private List<Consulta> consultas;
    private LayoutInflater layoutInflater;

    public AdapterTurnos(Context context, List<Consulta> consultas, LayoutInflater layoutInflater) {
        this.context = context;
        this.consultas = consultas;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public AdapterTurnos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root=layoutInflater.inflate(R.layout.turno,parent,false);
        return new AdapterTurnos.ViewHolder(root);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterTurnos.ViewHolder holder, int position) {

        holder.nombre.setText(consultas.get(position).getCliente_mascota().getMascota().getNombre());
        holder.fecha.setText(consultas.get(position).getTiempoInicio());

        Glide.with(context)
                .load("https://http.cat/images/102.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return consultas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre,fecha;
        ImageView imagen;
        Button btnDetalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.tvNomMascotaTurno);
            fecha=itemView.findViewById(R.id.tvFechaTurno);
            imagen=itemView.findViewById(R.id.ivMascotaTurno);
            btnDetalle=itemView.findViewById(R.id.btDetalleTurno);

            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundleConsulta = new Bundle();
                    Consulta consulta = consultas.get(getAdapterPosition());
                    bundleConsulta.putSerializable("consulta", consulta);

                    Navigation.findNavController( (Activity)context,R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_turnos_to_detalleTurnoFragment,bundleConsulta);
                }
            });
        }
    }
}
