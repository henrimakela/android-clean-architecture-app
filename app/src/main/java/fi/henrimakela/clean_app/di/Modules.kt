package fi.henrimakela.clean_app.di


import fi.henrimakela.clean_app.framework.ChordDataSourceImpl
import fi.henrimakela.data.repository.ChordDataSource
import fi.henrimakela.data.repository.ChordRepository
import fi.henrimakela.usecases.interactors.AddToFavorites
import fi.henrimakela.usecases.interactors.GetChordProgressions
import fi.henrimakela.usecases.interactors.GetFavouriteChordProgressions
import fi.henrimakela.usecases.interactors.RemoveFromFavorites
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module(override = true) {

    single {ChordDataSourceImpl(androidContext()) as ChordDataSource}
    single {ChordRepository(get())}
    single {GetChordProgressions(get())}
    single {AddToFavorites(get())}
    single {RemoveFromFavorites(get())}
    single {GetFavouriteChordProgressions(get())}
}

