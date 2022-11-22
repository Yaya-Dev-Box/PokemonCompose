package com.yayarh.pokemoncompose

import com.yayarh.pokemoncompose.di.DI
import com.yayarh.pokemoncompose.mocks.TestingPokeClient
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.sargunvohra.lib.pokekotlin.client.PokeApi
import javax.inject.Singleton

object TestingDi {

    @Module
    @TestInstallIn(components = [SingletonComponent::class], replaces = [DI.ClientModule::class])
    object TestingClientModule {
        @Singleton
        @Provides
        fun providePokeApiClient(): PokeApi = TestingPokeClient()
    }


}