package fi.henrimakela.clean_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.henrimakela.domain.ChordProgression
import fi.henrimakela.usecases.interactors.AddToFavorites
import fi.henrimakela.usecases.interactors.GetChordProgressions
import fi.henrimakela.usecases.interactors.GetFavouriteChordProgressions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ChordViewModel : ViewModel(), KoinComponent{

    private val getChordProgressions: GetChordProgressions by inject()
    private val addToFavorites: AddToFavorites by inject()
    private val getFavouriteChordProgressions: GetFavouriteChordProgressions by inject()

    private var _progressions = MutableLiveData<List<ChordProgression>>()
    private var _favourites = MutableLiveData<List<ChordProgression>>()

    val progressions: LiveData<List<ChordProgression>> get() = _progressions
    val favourites: LiveData<List<ChordProgression>> get() = _favourites

    init {
        loadChordProgressions()
        loadFavourites()
    }

    private fun loadChordProgressions(){

        GlobalScope.launch {
            _progressions.postValue(getChordProgressions())
        }
    }

    private fun loadFavourites(){
        GlobalScope.launch {
            _favourites.postValue(getFavouriteChordProgressions())
        }
    }

    fun addToFavorites(progression: ChordProgression){
        addToFavorites(progression)
        loadFavourites()
    }


}