package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.CharactersInformation.APIServicesTibia;
import com.example.TibiaTools.Repository.RepositoryCharacters;

public class ViewModelCharacters extends ViewModel {

    private final RepositoryCharacters repositoryCharacters = new RepositoryCharacters();

    private final MutableLiveData<APIServicesTibia> _characters = new MutableLiveData<>();

    public MutableLiveData<APIServicesTibia> characters() { return _characters; }

    public void setCharacters(String charName) {
        repositoryCharacters.characters(charName, _characters);
    }

}
