package fi.henrimakela.clean_app.framework

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fi.henrimakela.clean_app.framework.db.ChordDataBase
import fi.henrimakela.clean_app.framework.db.dao.FavoriteChordProgressionDao
import fi.henrimakela.clean_app.framework.db.entities.FavoriteChordProgression
import fi.henrimakela.data.repository.ChordDataSource
import fi.henrimakela.domain.ChordProgression
import java.io.IOException
import java.lang.reflect.Type

class ChordDataSourceImpl(private val context: Context) : ChordDataSource{

    private val db: ChordDataBase = ChordDataBase.getDatabase(context)
    private val dao: FavoriteChordProgressionDao = db.favoriteDao()

    override suspend fun getProgressions(): List<ChordProgression> {
        var jsonString = getJsonDataFromAsset(context, "chord_progressions.json")
        val founderListType: Type = object : TypeToken<ArrayList<ChordProgression?>?>() {}.type
        val gson = Gson()
        val chordList: ArrayList<ChordProgression> =
            gson.fromJson(jsonString, founderListType)

        return chordList
    }

    override suspend fun addToFavorites(progression: ChordProgression) {
        dao.insert(
            FavoriteChordProgression.mapFromChordProgression(progression)
        )
    }

    override suspend fun removeFromFavorites(progression: ChordProgression) {
        dao.delete(FavoriteChordProgression.mapFromChordProgression(progression))
    }

    override suspend fun getFavorites(): List<ChordProgression> {
       return dao.getAll().map {
           FavoriteChordProgression.mapToChordProgression(it)
       }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}