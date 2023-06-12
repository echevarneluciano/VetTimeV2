package com.example.vettime2.ui.inicio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vettime2.R;
import com.example.vettime2.modelos.Mascota;

import java.util.List;

public class AdapterMascotasInicio{  //extends RecyclerView.Adapter<AdapterMascotasInicio.ViewHolder> {

  /*  private Context context;
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

        holder.direccion.setText(inmuebles.get(position).getDireccion()+"");
        String esta = inmuebles.get(position).isEstado() ? "Activo" : "Inactivo";
        holder.estado.setText(esta);
        Glide.with(context)
                .load(inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView direccion,estado;
        ImageView imagen;
        Button btnDetalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            direccion=itemView.findViewById(R.id.tvDireccion);
            estado=itemView.findViewById(R.id.tvEstado);
            imagen=itemView.findViewById(R.id.ivImagen);
            btnDetalle=itemView.findViewById(R.id.btnDetalle);

            btnDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("inmueble", inmuebles.get(getAdapterPosition()));

                    Navigation.findNavController( (Activity)context,R.id.nav_host_fragment_content_escritorio).navigate(R.id.action_nav_inmuebles_to_descripcionFragment,bundle);
                }
            });
        }
    }*/

}
