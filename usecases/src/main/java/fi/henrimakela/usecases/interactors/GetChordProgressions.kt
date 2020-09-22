package fi.henrimakela.usecases.interactors

import fi.henrimakela.data.repository.ChordRepository
import fi.henrimakela.domain.ChordProgression

class GetChordProgressions(private val repository: ChordRepository) {
    
    suspend operator fun invoke(): List<ChordProgression>{
        return repository.getAllChords()
    }

}