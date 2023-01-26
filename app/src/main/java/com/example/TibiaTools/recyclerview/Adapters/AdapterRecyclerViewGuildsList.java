package com.example.TibiaTools.recyclerview.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewGuilds;
import com.example.ttools.R;

import java.util.List;

public class AdapterRecyclerViewGuildsList extends RecyclerView.Adapter<AdapterRecyclerViewGuildsList.ViewHolder> implements View.OnClickListener {
    //Creando el arraylist de objetos de los datos del recyclerview Criatures
    private List<ItemsRecyclerViewGuilds> items_guilds_list;

    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /*
     * IMPLEMENTACION DE LOS METODOS CLICK Y LONGCLICK
     * */
    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }


    //constructor
    public AdapterRecyclerViewGuildsList(List<ItemsRecyclerViewGuilds> items_guilds_list){
        this.items_guilds_list = items_guilds_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_items_guilds,parent,false);
        vista.setOnClickListener(this);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lbNombre.setText(items_guilds_list.get(position).getLbName());
        holder.lbDescripcion.setText(items_guilds_list.get(position).getLbDescripcion());
        String urlImage = items_guilds_list.get(position).getLbLogoUrl();
        Glide.with(holder.imgLogo.getContext()).load(urlImage).asGif().into(holder.imgLogo);
    }

    @Override
    public int getItemCount() {
        return items_guilds_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgLogo;
        TextView lbNombre, lbDescripcion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imageViewLogo);
            lbNombre = itemView.findViewById(R.id.textViewName);
            lbDescripcion = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
