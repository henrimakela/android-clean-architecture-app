package fi.henrimakela.data.repository
import fi.henrimakela.domain.ChordProgression

class ChordRepository(private val dataSource: ChordDataSource){

    suspend fun getAllChords(): List<ChordProgression>{
        val progressions = dataSource.getProgressions()

        //sort to alphabetical order
        var sortedList = ArrayList<ChordProgression>(progressions.sortedWith(compareBy {it.style}))
        return sortedList
    }

    suspend fun getFavourites(): List<ChordProgression> = dataSource.getFavorites()

    suspend fun saveToFavourites(progression: ChordProgression) = dataSource.addToFavorites(progression)

    suspend fun removeFromFavourites(progression: ChordProgression) = dataSource.removeFromFavorites(progression)

}