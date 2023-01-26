package com.example.TibiaTools.recyclerview.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TibiaTools.recyclerview.ItemsRecyclerViewNews;
import com.example.ttools.R;

import java.util.List;

public class AdapterRecyclerViewNews extends RecyclerView.Adapter<AdapterRecyclerViewNews.ViewHolder> {
    private List<ItemsRecyclerViewNews> items_news;

    public AdapterRecyclerViewNews(List<ItemsRecyclerViewNews> items_news){
        this.items_news = items_news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_news_items,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemsRecyclerViewNews itemsRecyclerViewNews = (ItemsRecyclerViewNews) items_news.get(position);
        holder.textViewFecha.setText(itemsRecyclerViewNews.getDate());
        holder.textViewNoticia.setText(itemsRecyclerViewNews.getNews());
        holder.textViewCategoria.setText(itemsRecyclerViewNews.getCategory());
        holder.textViewTipo.setText(itemsRecyclerViewNews.getType());
    }

    @Override
    public int getItemCount() {
        return items_news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFecha,textViewCategoria, textViewNoticia,textViewTipo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
            textViewCategoria = itemView.findViewById(R.id.textViewCategoria);
            textViewNoticia = itemView.findViewById(R.id.textViewNoticia);
            textViewTipo = itemView.findViewById(R.id.textViewTipo);
        }
    }
}
