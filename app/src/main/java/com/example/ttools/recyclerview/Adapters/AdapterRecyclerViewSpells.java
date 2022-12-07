package com.example.ttools.recyclerview.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttools.R;
import com.example.ttools.recyclerview.ItemsRecyclerViewSpells;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterRecyclerViewSpells extends RecyclerView.Adapter<AdapterRecyclerViewSpells.ViewHolder> {
    List<ItemsRecyclerViewSpells> items_spells;
    // para formatear numeros a formato de dinero.
    DecimalFormat decimalFormat = new DecimalFormat("###,###.00");

    public AdapterRecyclerViewSpells(List<ItemsRecyclerViewSpells> items_spells){
        this.items_spells = items_spells;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_spells_tibia,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemsRecyclerViewSpells itemsRecyclerViewSpells = (ItemsRecyclerViewSpells) items_spells.get(position);
        holder.lbName.setText(itemsRecyclerViewSpells.getNombre());
        holder.lbFormula.setText(itemsRecyclerViewSpells.getFormula());
        holder.lbNivel.setText("Level: "+itemsRecyclerViewSpells.getLevel());
        holder.lbMana.setText("Mana: "+itemsRecyclerViewSpells.getMana());
        holder.lbPrecio.setText(decimalFormat.format(Integer.parseInt(itemsRecyclerViewSpells.getPrecio())));
        holder.lbType.setText(itemsRecyclerViewSpells.getTipo());
        holder.lbGrupo.setText(itemsRecyclerViewSpells.getGrupo());
        holder.lbSpell_Id.setText(itemsRecyclerViewSpells.getSpellId());
        holder.lbSpell_Id.setVisibility(View.INVISIBLE);
        holder.lbPremium.setText(itemsRecyclerViewSpells.getPremium());

    }

    @Override
    public int getItemCount() {
        return items_spells.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lbName, lbFormula, lbNivel, lbMana, lbPrecio, lbType,
        lbGrupo, lbSpell_Id, lbPremium;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lbName = itemView.findViewById(R.id.textViewName);
            lbFormula = itemView.findViewById(R.id.textViewFormula);
            lbNivel = itemView.findViewById(R.id.textViewNivvel);
            lbMana = itemView.findViewById(R.id.textViewMana);
            lbPrecio = itemView.findViewById(R.id.textViewPrecio);
            lbType = itemView.findViewById(R.id.textViewType);
            lbGrupo = itemView.findViewById(R.id.textViewGroup);
            lbSpell_Id = itemView.findViewById(R.id.textViewSpell_Id);
            lbPremium = itemView.findViewById(R.id.textViewPremium);
        }
    }
}
