package fi.henrimakela.clean_app.framework

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fi.henrimakela.data.repository.ChordDataSource
import fi.henrimakela.domain.ChordProgression
import java.io.IOException
import java.lang.reflect.Type

class ChordDataSourceImpl(private val context: Context) : ChordDataSource{

    override suspend fun getProgressions(): List<ChordProgression> {

        var jsonString = getJsonDataFromAsset(context, "chord_progressions.json")
        val founderListType: Type = object : TypeToken<ArrayList<ChordProgression?>?>() {}.type
        val gson = Gson()
        val chordList: ArrayList<ChordProgression> =
            gson.fromJson(jsonString, founderListType)

        //sort to alphabetical order
        var sortedList = ArrayList<ChordProgression>(chordList.sortedWith(compareBy {it.style}))
        return sortedList
    }

    override suspend fun addToFavorites(progression: ChordProgression) {

    }

    override suspend fun removeFromFavorites(progression: ChordProgression) {

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