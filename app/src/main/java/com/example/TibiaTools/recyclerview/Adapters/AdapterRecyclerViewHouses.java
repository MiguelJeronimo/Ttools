package com.example.TibiaTools.recyclerview.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TibiaTools.recyclerview.ItemsRecyclerViewHouses;
import com.example.ttools.R;

import java.util.List;
import java.util.Objects;

public class AdapterRecyclerViewHouses extends RecyclerView.Adapter<AdapterRecyclerViewHouses.ViewHolder> implements View.OnClickListener {
    List<ItemsRecyclerViewHouses> items_hoses;
    private View.OnClickListener listener;
    public void setOnClickListener(View.OnClickListener listener){this.listener=listener;}
    public AdapterRecyclerViewHouses(List<ItemsRecyclerViewHouses> items_hoses){this.items_hoses = items_hoses;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_items_houses,parent,false);
        vista.setOnClickListener(this);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.house_name.setText(items_hoses.get(position).getHouseName());
        holder.house_size.setText(items_hoses.get(position).getHouseSize());
        holder.house_rent.setText(items_hoses.get(position).getHouseRent());
        if (Objects.equals(items_hoses.get(position).getHouseRented(), "Ocupada")){
            holder.house_rented.setTextColor(Color.parseColor("#00FF00"));
        } else{
            holder.house_rented.setTextColor(Color.parseColor("#F10300"));
        }
        holder.house_rented.setText(items_hoses.get(position).getHouseRented());
        holder.house_id.setText(items_hoses.get(position).getHouseId());
    }

    @Override
    public int getItemCount() {
        return items_hoses.size();
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView house_name,house_size, house_rent, house_rented,house_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            house_name = itemView.findViewById(R.id.house_name);
            house_size = itemView.findViewById(R.id.house_size);
            house_rent = itemView.findViewById(R.id.house_rent);
            house_rented = itemView.findViewById(R.id.house_rented);
            house_id = itemView.findViewById(R.id.house_id);
        }
}
}
