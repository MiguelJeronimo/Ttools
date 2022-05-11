package com.example.ttools.recyclerview.Adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttools.R;
import com.example.ttools.recyclerview.ItemsRecyclerViewMundos;

import java.util.List;

public class adapterRecyclerviewMundos extends RecyclerView.Adapter<adapterRecyclerviewMundos.ViewHolder> {
    //Creando el arraylist de objetos de los datos del recyclerview
    private List<ItemsRecyclerViewMundos> items_mundos;

    public adapterRecyclerviewMundos(List<ItemsRecyclerViewMundos> items_mundos){
        this.items_mundos = items_mundos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_mundo,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //agregando información a los componentes del item de recyclerview
        final ItemsRecyclerViewMundos itemsRecyclerViewMundos = (ItemsRecyclerViewMundos) items_mundos.get(position);
        holder.lbName.setText("Name: "+itemsRecyclerViewMundos.getLbName());
        holder.lbStatus.setText("Status: "+itemsRecyclerViewMundos.getLbStatus());
        holder.lbLocation.setText("Location: "+itemsRecyclerViewMundos.getLbLocation());
        holder.lbPvpType.setText("Pvp type: "+itemsRecyclerViewMundos.getLbPvpType());
        holder.lbPremiumOnly.setText("Premium only: "+itemsRecyclerViewMundos.getLbPremiumOnly());
        holder.lbTransferType.setText("Transfer type: "+itemsRecyclerViewMundos.getLbTransferType());
        holder.lbPlayersOnline.setText("Players Online: "+itemsRecyclerViewMundos.getLbPlayersOnline());
        holder.lbGameWorlType.setText("Game world type: "+itemsRecyclerViewMundos.getLbGameWorlType());
        holder.lbBattleyeProtected.setText("BattleEye protected: "+itemsRecyclerViewMundos.getLbBattleyeProtected());
        holder.lbBattleyeDate.setText("BattleEye date: "+itemsRecyclerViewMundos.getLbBattleyeDate());
        holder.lbTournamentWorldType.setText("Tournament world type"+itemsRecyclerViewMundos.getLbTournamentWorldType());
    }

    @Override
    public int getItemCount() {
        return items_mundos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //inicializando los componentes del item de recyclerview
        TextView lbName, lbStatus, lbPlayersOnline,
                 lbLocation, lbPvpType, lbPremiumOnly,
                 lbTransferType, lbBattleyeProtected, lbBattleyeDate,
                 lbGameWorlType, lbTournamentWorldType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lbName = itemView.findViewById(R.id.world);
            lbStatus = itemView.findViewById(R.id.status);
            lbLocation = itemView.findViewById(R.id.location);
            lbPvpType = itemView.findViewById(R.id.pvptype);
            lbPremiumOnly = itemView.findViewById(R.id.premium_only);
            lbTransferType = itemView.findViewById(R.id.transfer_type);
            lbPlayersOnline = itemView.findViewById(R.id.player_online);
            lbGameWorlType = itemView.findViewById(R.id.game_world_type);
            lbBattleyeProtected = itemView.findViewById(R.id.battleeye_protected);
            lbBattleyeDate = itemView.findViewById(R.id.battleeye_date);
            lbTournamentWorldType = itemView.findViewById(R.id.tournament_world_type);
        }
    }
}

