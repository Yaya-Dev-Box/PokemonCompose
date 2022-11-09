package com.yayarh.pokemoncompose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class HomeVm : ViewModel() {

    private val client = PokeApiClient()

    val pokemonList = MutableStateFlow(emptyList<Pokemon>())

    init {
        get10Pokemon()
    }

    private fun get10Pokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            println("Querying data")
            val pokemon = client.getPokemonList(0, 12).results
            pokemonList.value = pokemon.map { async { client.getPokemon(it.id) } }.awaitAll()
        }
    }

}