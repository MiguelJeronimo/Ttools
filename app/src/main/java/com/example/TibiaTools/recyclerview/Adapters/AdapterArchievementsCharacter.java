package com.example.TibiaTools.recyclerview.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TibiaTools.recyclerview.ItemsArchievementsCharacter;
import com.example.ttools.R;

import java.util.List;

public class AdapterArchievementsCharacter extends RecyclerView.Adapter<AdapterArchievementsCharacter.ViewHolder> {
    private List<ItemsArchievementsCharacter> itemsArchievementsCharacters;
    Boolean isSecret;

    public AdapterArchievementsCharacter(List<ItemsArchievementsCharacter> itemsArchievementsCharacters){
        this.itemsArchievementsCharacters = itemsArchievementsCharacters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_achievements_character,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.achievementsName.setText(itemsArchievementsCharacters.get(position).getAchievementsName());
        holder.achievementsGrade.setText(itemsArchievementsCharacters.get(position).getAchievementsGrade());
        isSecret = itemsArchievementsCharacters.get(position).isSecret();
        if (isSecret) {
            holder.achievementsSecret.setText("Secret");
        }
    }

    @Override
    public int getItemCount() {
        return itemsArchievementsCharacters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView achievementsName, achievementsGrade, achievementsSecret;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            achievementsName = itemView.findViewById(R.id.achievementsName);
            achievementsGrade = itemView.findViewById(R.id.achievementsGrade);
            achievementsSecret = itemView.findViewById(R.id.achievementsSecret);
        }
    }
}
