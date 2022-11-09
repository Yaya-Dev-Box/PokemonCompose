package com.yayarh.pokemoncompose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yayarh.pokemoncompose.presentation.home.HomeVm.HomeState.Idle
import com.yayarh.pokemoncompose.presentation.home.HomeVm.HomeState.InitialLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class HomeVm : ViewModel() {

    private val client = PokeApiClient()

    private val _state = MutableStateFlow<HomeState>(InitialLoading)
    val state = _state.asStateFlow()

    private val _pokemonList = MutableStateFlow(emptyList<Pokemon>())
    val pokemonList = _pokemonList.asStateFlow()


    init {
        get10Pokemon()
    }

    private fun get10Pokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            println("Querying data")

            _state.value = InitialLoading
            val pokemon = client.getPokemonList(0, 12).results
            _pokemonList.value = pokemon.map { async { client.getPokemon(it.id) } }.awaitAll()
            _state.value = Idle
        }
    }

    sealed interface HomeState {
        object InitialLoading : HomeState
        object Idle : HomeState
        object LoadingMore : HomeState
    }

}