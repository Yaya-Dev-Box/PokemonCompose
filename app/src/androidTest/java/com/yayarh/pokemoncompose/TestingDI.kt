package com.yayarh.pokemoncompose

import com.yayarh.pokemoncompose.di.DI
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import me.sargunvohra.lib.pokekotlin.client.PokeApi
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import javax.inject.Singleton

object TestingDI {

    @Module
    @TestInstallIn(components = [SingletonComponent::class], replaces = [DI.ClientModule::class])
    object TestingClientModule {
        @Singleton
        @Provides
        fun providePokeApi(): PokeApi = mockk<PokeApiClient>()
    }


}