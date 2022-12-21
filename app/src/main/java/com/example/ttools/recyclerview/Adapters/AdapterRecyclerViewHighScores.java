package com.example.ttools.recyclerview.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttools.R;
import com.example.ttools.recyclerview.ItemsRecyclerViewHighScores;

import java.util.List;

public class AdapterRecyclerViewHighScores extends RecyclerView.Adapter<AdapterRecyclerViewHighScores.ViewHolder>{
    List<ItemsRecyclerViewHighScores> highscores;

    public AdapterRecyclerViewHighScores(List<ItemsRecyclerViewHighScores> highscores){this.highscores = highscores;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_items_highscores, parent, false);
        return new ViewHolder(Vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewRank.setText(highscores.get(position).getRank());
        holder.textViewName.setText(highscores.get(position).getName());
        holder.textViewVocation.setText(highscores.get(position).getVocation());
        holder.textViewLevel.setText(highscores.get(position).getLevel());
        holder.textViewValue.setText(highscores.get(position).getValue());
        holder.textViewTittle.setText(highscores.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return highscores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRank,textViewName,textViewVocation,textViewLevel,textViewValue,textViewTittle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRank = itemView.findViewById(R.id.textViewRank);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewVocation = itemView.findViewById(R.id.textViewVocation);
            textViewLevel = itemView.findViewById(R.id.textViewLevel);
            textViewValue = itemView.findViewById(R.id.textViewValue);
            textViewTittle = itemView.findViewById(R.id.textViewTittle);
        }
    }
}
