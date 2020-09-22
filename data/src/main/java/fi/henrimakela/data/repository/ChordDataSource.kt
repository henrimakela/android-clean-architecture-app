package fi.henrimakela.data.repository

import fi.henrimakela.domain.ChordProgression


//For communication with framework layer e.g Room
interface ChordDataSource {

    suspend fun getProgressions(): List<ChordProgression>

    suspend fun addToFavorites(progression: ChordProgression)

    suspend fun removeFromFavorites(progression: ChordProgression)


}