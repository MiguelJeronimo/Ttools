package com.example.ttools.recyclerview.Adapters;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttools.R;
import com.example.ttools.recyclerview.itemsRecyclerViewGuildsName;

import java.util.List;

public class AdapterRecyclerViewGuildName extends RecyclerView.Adapter<AdapterRecyclerViewGuildName.ViewHolder> {
    //Creando el arraylist de objetos de los datos del recyclerview Criatures
    private List<itemsRecyclerViewGuildsName> items_guilds_name;

    public AdapterRecyclerViewGuildName(List<itemsRecyclerViewGuildsName> items_guilds_name){
        this.items_guilds_name = items_guilds_name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_guilds_members, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(items_guilds_name.get(position).getName());
        holder.txtTitle.setText(items_guilds_name.get(position).getTitle());
        holder.txtRank.setText(items_guilds_name.get(position).getRank());
        holder.txtVocation.setText(items_guilds_name.get(position).getVocation());
        holder.txtLevel.setText(items_guilds_name.get(position).getLevel());
        holder.txtStatus.setText(items_guilds_name.get(position).getStatus());
        holder.txtJouned.setText(items_guilds_name.get(position).getJoined());
    }

    @Override
    public int getItemCount() {
        return items_guilds_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtTitle, txtRank, txtVocation, txtLevel, txtJouned, txtStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textViewGuildName);
            txtTitle = itemView.findViewById(R.id.textViewRace);
            txtRank = itemView.findViewById(R.id.textViewRango);
            txtVocation = itemView.findViewById(R.id.textViewTitulo);
            txtLevel = itemView.findViewById(R.id.textViewLevel);
            txtRank = itemView.findViewById(R.id.textViewRango);
            txtStatus = itemView.findViewById(R.id.textViewStatus);
            txtJouned = itemView.findViewById(R.id.textViewUnido);

        }
    }
}
