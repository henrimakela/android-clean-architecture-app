package fi.henrimakela.usecases.interactors

import fi.henrimakela.data.repository.ChordRepository
import fi.henrimakela.domain.ChordProgression

class AddToFavorites(private val repository: ChordRepository) {

    suspend operator fun invoke(progression: ChordProgression) {
        return repository.saveToFavourites(progression)
    }

}