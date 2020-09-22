package fi.henrimakela.usecases.interactors

import fi.henrimakela.data.repository.ChordRepository
import fi.henrimakela.domain.ChordProgression

class RemoveFromFavorites(private val repository: ChordRepository) {

    suspend operator fun invoke(progression: ChordProgression){
        return repository.removeFromFavourites(progression)
    }

}