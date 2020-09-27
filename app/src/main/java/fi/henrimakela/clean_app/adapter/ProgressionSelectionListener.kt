package fi.henrimakela.clean_app.adapter

import fi.henrimakela.domain.ChordProgression

interface ProgressionSelectionListener {

    fun addToFavourites(progression: ChordProgression): Boolean
    fun progressionSelected(progression: ChordProgression)

}