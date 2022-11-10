package com.yayarh.pokemoncompose.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yayarh.pokemoncompose.presentation.home.HomeVm.HomeState.Idle
import com.yayarh.pokemoncompose.presentation.home.HomeVm.HomeState.InitialLoading
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.Pokemon

class HomeVm : ViewModel() {

    private val client = PokeApiClient()

    private val _state = MutableStateFlow<HomeState>(Idle)
    val state = _state.asStateFlow()

    private val _pokemonList = MutableStateFlow(emptyList<Pokemon>())
    val pokemonList = _pokemonList.asStateFlow()

    var isRefreshing = mutableStateOf<Boolean?>(null)
        private set

    init {
        loadPokemonList(refreshData = true)
    }

    fun loadPokemonList(refreshData: Boolean) {
        if (state.value is HomeState.LoadingMore || state.value is InitialLoading) return

        if (refreshData && pokemonList.value.isNotEmpty()) isRefreshing.value =
            true // If "isRefreshing" is null, tha means we haven't loaded anything yet

        viewModelScope.launch(Dispatchers.IO) {
            _state.value = if (refreshData) InitialLoading else HomeState.LoadingMore
            val currentList = if (refreshData) emptyList() else pokemonList.value

            delay(1500)

            println("Querying data: starting from pokemon ${currentList.size}")

            val pokemon = client.getPokemonList(currentList.size, 10).results

            _pokemonList.value = currentList + pokemon.map { async { client.getPokemon(it.id) } }.awaitAll()
            _state.value = Idle
            isRefreshing.value = false
        }
    }

    sealed interface HomeState {
        object InitialLoading : HomeState
        object Idle : HomeState
        object LoadingMore : HomeState
    }

}