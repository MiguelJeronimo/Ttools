package com.example.TibiaTools.recyclerview.Adapters;

import android.graphics.Color;
import android.text.BoringLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TibiaTools.recyclerview.ItemsCharacters;
import com.example.ttools.R;

import java.util.List;


public class AdapterOtherCharacters extends RecyclerView.Adapter<AdapterOtherCharacters.ViewHolder> {
    List<ItemsCharacters> itemsCharacters;
    Boolean isMain, isDelete;
    String status;
    public AdapterOtherCharacters(List<ItemsCharacters> itemsCharacters){
        this.itemsCharacters = itemsCharacters;
    }
    @NonNull
    @Override
    public AdapterOtherCharacters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_characters_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOtherCharacters.ViewHolder holder, int position) {
        holder.nameCharater.setText(itemsCharacters.get(position).getName());
        status = itemsCharacters.get(position).getStatus();
        holder.characterStatus.setText(status);
        if (status.equals("online")){
            holder.characterStatus.setTextColor(Color.GREEN);
        }
        isMain = itemsCharacters.get(position).getMain();
        isDelete = itemsCharacters.get(position).getDeleted();
        if (isMain){
            holder.characterMain.setText("Main");
            holder.characterMain.setTextColor(Color.parseColor("#FFB4AB"));
        }
        if (isDelete){
            holder.characterDelete.setText("Delete");
        }
        holder.characterWorld.setText(itemsCharacters.get(position).getWorld());
    }

    @Override
    public int getItemCount() {
        return itemsCharacters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameCharater, characterStatus, characterDelete, characterMain, characterWorld;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCharater = itemView.findViewById(R.id.nameCharater);
            characterStatus = itemView.findViewById(R.id.characterStatus);
            characterDelete = itemView.findViewById(R.id.characterDelete);
            characterMain = itemView.findViewById(R.id.characterMain);
            characterWorld = itemView.findViewById(R.id.characterWorld);
        }
    }
}
