package fi.henrimakela.clean_app.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.henrimakela.clean_app.framework.db.dao.FavoriteChordProgressionDao
import fi.henrimakela.clean_app.framework.db.entities.FavoriteChordProgression

@Database(entities = [FavoriteChordProgression::class], version = 1, exportSchema = false)
abstract class ChordDataBase : RoomDatabase(){
    abstract fun favoriteDao(): FavoriteChordProgressionDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ChordDataBase? = null

        fun getDatabase(context: Context): ChordDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChordDataBase::class.java,
                    "chord_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}