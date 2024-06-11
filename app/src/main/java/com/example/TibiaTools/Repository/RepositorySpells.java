package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.ApiSpells;
import com.example.TibiaTools.APISERVER.models.ApiSpellsInformation;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList.Spell;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.Spells;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositorySpells extends Repository{
    public void spells(MutableLiveData<Spells> _spells){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiSpells> call = tibiaAPIServer.getSpells();
        call.enqueue(new Callback<ApiSpells>() {
            @Override
            public void onResponse(@NonNull Call<ApiSpells> call, @NonNull Response<ApiSpells> response) {
                if(response.isSuccessful()){
                    ApiSpells apiSpells = response.body();
                    Spells spells = apiSpells.getSpells();
                    _spells.setValue(spells);
                } else {
                    _spells.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiSpells> call, @NonNull Throwable t) {
                _spells.setValue(null);
            }
        });
    }
    public void spell(String id, MutableLiveData<Spell> _spell){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiSpellsInformation> call = tibiaAPIServer.getSpellInformation(id);
        call.enqueue(new Callback<ApiSpellsInformation>() {
            @Override
            public void onResponse(@NonNull Call<ApiSpellsInformation> call, @NonNull Response<ApiSpellsInformation> response) {
                if (response.isSuccessful()){
                    ApiSpellsInformation apiSpellsInformation = response.body();
                    assert apiSpellsInformation != null;
                    Spell spell = apiSpellsInformation.getSpells();
                    _spell.setValue(spell);
                } else {
                    _spell.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiSpellsInformation> call, @NonNull Throwable t) {
                _spell.setValue(null);
            }
        });
    }
}
