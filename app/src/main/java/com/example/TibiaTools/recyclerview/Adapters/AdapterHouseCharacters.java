package com.example.TibiaTools.recyclerview.Adapters;

import static android.os.Build.VERSION_CODES.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.TibiaTools.recyclerview.ItemsHousesCharacters;
import com.example.ttools.R;

import java.util.List;

public class AdapterHouseCharacters extends RecyclerView.Adapter<AdapterHouseCharacters.ViewHolder> {
    List<ItemsHousesCharacters> items_hoses;

    public AdapterHouseCharacters(List<ItemsHousesCharacters> items_hoses){
        this.items_hoses = items_hoses;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.ttools.R.layout.items_houses_recyclerview_characters,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.houseName.setText(items_hoses.get(position).getName());
        holder.houseCity.setText(items_hoses.get(position).getTown());
        holder.houseDateRent.setText(items_hoses.get(position).getPaid());
        holder.idHouseRent.setText(String.valueOf(items_hoses.get(position).getHouseid()));
    }

    @Override
    public int getItemCount() {
        return items_hoses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView houseName, houseCity, houseDateRent, idHouseRent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            houseName = itemView.findViewById(com.example.ttools.R.id.houseName);
            houseCity = itemView.findViewById(com.example.ttools.R.id.houseCity);
            houseDateRent = itemView.findViewById(com.example.ttools.R.id.houseDateRent);
            idHouseRent = itemView.findViewById(com.example.ttools.R.id.idHouseRent);
        }
    }
}
