package fi.henrimakela.clean_app.framework.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fi.henrimakela.clean_app.framework.db.entities.FavoriteChordProgression


@Dao
interface FavoriteChordProgressionDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<FavoriteChordProgression>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(progression: FavoriteChordProgression)

    @Delete
    suspend fun delete(progression: FavoriteChordProgression)

}