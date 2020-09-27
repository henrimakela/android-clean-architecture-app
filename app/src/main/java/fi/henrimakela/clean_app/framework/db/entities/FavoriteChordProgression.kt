package fi.henrimakela.clean_app.framework.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import fi.henrimakela.domain.ChordProgression

@Entity(tableName = "favorites")
data class FavoriteChordProgression(
    @ColumnInfo(name = "style") var style: String?,
    @ColumnInfo(name = "progression") var progression: String?,
    @ColumnInfo(name = "transposable") var transposable: Boolean?
){
    @PrimaryKey(autoGenerate = true) var uid: Int = 0

    companion object{
        fun mapFromChordProgression(progression: ChordProgression): FavoriteChordProgression{
            return FavoriteChordProgression(
                progression.style,
                progression.progression,
                progression.transposable
            )
        }

        fun mapToChordProgression(progression: FavoriteChordProgression): ChordProgression{
            return ChordProgression(
                progression.style,
                progression.progression,
                progression.transposable
            )
        }
    }
}