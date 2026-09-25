package com.example.TibiaTools.di

import com.example.TibiaTools.data.repository.TibiaRepositoryImpl
import com.example.TibiaTools.data.retrofit.TibiaAPIServer
import com.example.TibiaTools.domain.repository.TibiaRepository
import com.example.TibiaTools.domain.usecase.GetCharacterUseCase
import com.example.TibiaTools.domain.usecase.GetCreaturesUseCase
import com.example.TibiaTools.domain.usecase.GetNewsLatestUseCase
import com.example.TibiaTools.domain.usecase.GetWorldsUseCase
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
    factory { GetNewsLatestUseCase(get()) }
}
