package com.example.ttools.recyclerview.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttools.R;
import com.example.ttools.recyclerview.ItemsRecyclerViewCriatures;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterRecyclerViewCriatures extends RecyclerView.Adapter<adapterRecyclerViewCriatures.ViewHolder> implements View.OnClickListener{
    //Creando el arraylist de objetos de los datos del recyclerview Criatures
    private List<ItemsRecyclerViewCriatures> items_criatures;
    //objetos de eventos click
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


    //Constructor
    public adapterRecyclerViewCriatures(List<ItemsRecyclerViewCriatures> items_criatures){
        this.items_criatures = items_criatures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_criatures,parent,false);
        vista.setOnClickListener(this);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lbName.setText(items_criatures.get(position).getLbName());
        holder.lbRace.setText("Race: "+items_criatures.get(position).getLbrace());
        String urlImagen = items_criatures.get(position).getImagerace();
        //Agregando imagen por url utilizando la libreria picasso
        Picasso.get().load(urlImagen).into(holder.imageCriature);
        //Glide.with(holder.imageCriature.getContext()).load(urlImagen).asGif().into(holder.imageCriature);
    }

    @Override
    public int getItemCount() {
        return items_criatures.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lbName, lbRace;
        ImageView imageCriature;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lbName = itemView.findViewById(R.id.textViewName);
            lbRace = itemView.findViewById(R.id.textViewRace);
            imageCriature = itemView.findViewById(R.id.imageViewCriature);
        }
    }
}
