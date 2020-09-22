package fi.henrimakela.clean_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.henrimakela.domain.ChordProgression
import fi.henrimakela.usecases.interactors.GetChordProgressions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ChordViewModel : ViewModel(), KoinComponent{

    private val getChordProgressions: GetChordProgressions by inject()
    private var _progressions = MutableLiveData<List<ChordProgression>>()

    val progressions: LiveData<List<ChordProgression>> get() = _progressions

    init {
        loadChordProgressions()
    }

    private fun loadChordProgressions(){

        GlobalScope.launch {
            _progressions.postValue(getChordProgressions())
        }
    }
}