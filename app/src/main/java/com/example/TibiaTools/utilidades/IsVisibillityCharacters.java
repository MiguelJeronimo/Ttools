package com.example.TibiaTools.utilidades;

import android.view.View;

import com.example.ttools.R;
import com.example.ttools.databinding.ActivityCharactersBinding;

public class IsVisibillityCharacters {

    private boolean isVisibility;

    public boolean isVisibility() {
        return isVisibility;
    }

    public void setVisibility(boolean visibility) {
        isVisibility = visibility;
    }

    public void VisibilityDataGeneral (ActivityCharactersBinding binding){
        if (isVisibility()){
            System.out.println("ENTRO AL VISIBILITYU"+ isVisibility());
            binding.getRoot().findViewById(R.id.textView2).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.nombre).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.textView5).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.sexo).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.textView6).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.titulo).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.vocacion).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.textView4).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.textView7).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.nivel).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.textView11).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.achivement).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.mundo).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.textView10).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.textView15).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.residencia).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.textView18).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.separador).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.guild).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.textView22).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.last_loguin).setVisibility(View.VISIBLE);
            binding.getRoot().findViewById(R.id.separador).setVisibility(View.VISIBLE);
        } else{
            binding.getRoot().findViewById(R.id.textView2).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.nombre).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView5).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.sexo).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView6).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.titulo).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.vocacion).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView4).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView7).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.nivel).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView11).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.achivement).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.mundo).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView10).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView15).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.residencia).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView18).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.separador).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.guild).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView22).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.last_loguin).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.separador).setVisibility(View.GONE);
        }
    }
}
