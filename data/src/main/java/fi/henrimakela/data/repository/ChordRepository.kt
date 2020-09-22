package fi.henrimakela.data.repository
import fi.henrimakela.domain.ChordProgression

class ChordRepository(private val dataSource: ChordDataSource){

    suspend fun getAllChords() = dataSource.getProgressions()

    suspend fun saveToFavourites(progression: ChordProgression) = dataSource.addToFavorites(progression)

    suspend fun removeFromFavourites(progression: ChordProgression) = dataSource.removeFromFavorites(progression)

}