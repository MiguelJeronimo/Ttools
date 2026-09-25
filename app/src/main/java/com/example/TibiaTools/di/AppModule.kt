package com.example.TibiaTools.di

import com.example.TibiaTools.View.ViewModel.*
import com.example.TibiaTools.data.repository.TibiaRepositoryImpl
import com.example.TibiaTools.data.retrofit.TibiaAPIServer
import com.example.TibiaTools.domain.repository.TibiaRepository
import com.example.TibiaTools.domain.usecase.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val appModule = module {
    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://api.tibiadata.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API Server
    single<TibiaAPIServer> {
        get<Retrofit>().create(TibiaAPIServer::class.java)
    }

    // Repository
    single<TibiaRepository> {
        TibiaRepositoryImpl(get())
    }

    // Use Cases
    factory { GetCharacterUseCase(get()) }
    factory { GetWorldsUseCase(get()) }
    factory { GetCreaturesUseCase(get()) }
    factory { GetBoostableBossesUseCase(get()) }
    factory { GetCreatureInformationUseCase(get()) }
    factory { GetGuildsInformationUseCase(get()) }
    factory { GetGuildsInformationNameUseCase(get()) }
    factory { GetSpellsUseCase(get()) }
    factory { GetSpellInformationUseCase(get()) }
    factory { GetRashidLocationUseCase(get()) }
    factory { GetNewsLatestUseCase(get()) }
    factory { GetNewsTickersUseCase(get()) }
    factory { GetHighScoreInformationUseCase(get()) }
    factory { GetHousesInformationUseCase(get()) }
    factory { GetHouseInformationUseCase(get()) }

    // ViewModels
    viewModel { ViewModelCharacters(get()) }
    viewModel { ViewModelCreature(get()) }
    viewModel { ViewModelCreatures(get()) }
    viewModel { ViewModelGuildInformation(get()) }
    viewModel { ViewModelGuilds(get(), get()) }
    viewModel { ViewModelHighScore(get(), get()) }
    viewModel { ViewModelHome(get(), get(), get(), get(), get(), get()) }
    viewModel { ViewModelHouseInformation(get()) }
    viewModel { ViewModelHouses(get(), get()) }
    viewModel { ViewModelSpells(get()) }
    viewModel { ViewModelWorlds(get()) }
    viewModel { ViewModelSpell(get()) }
}
