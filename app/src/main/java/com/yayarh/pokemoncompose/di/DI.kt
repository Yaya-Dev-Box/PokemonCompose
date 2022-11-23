package com.yayarh.pokemoncompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.sargunvohra.lib.pokekotlin.client.PokeApi
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import javax.inject.Singleton

object DI {

    @InstallIn(SingletonComponent::class)
    @Module
    object ClientModule {
        @Singleton
        @Provides
        fun providePokeApi(): PokeApi = PokeApiClient()
    }

}