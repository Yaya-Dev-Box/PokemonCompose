package com.yayarh.pokemoncompose.presentation.home

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yayarh.pokemoncompose.presentation.home.HomeVm.HomeState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.sargunvohra.lib.pokekotlin.client.PokeApi
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor(private val client: PokeApi) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(Idle)
    val state = _state.asStateFlow()

    private val _pokemonList = MutableStateFlow(emptyList<Pokemon>())
    val pokemonList = _pokemonList.asStateFlow()

    init {
        loadPokemonList(refreshData = true)
    }

    fun loadPokemonList(refreshData: Boolean) {
        if (state.value is LoadingMore || state.value is InitialLoading) return

        _state.value = if (refreshData) InitialLoading else LoadingMore

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentList = if (refreshData) emptyList() else pokemonList.value

                println("Querying data: starting from pokemon ${currentList.size}")
                val remoteList = client.getPokemonList(currentList.size, 10).results

                _pokemonList.value = currentList + fetchPokemonsDetails(remoteList)

                _state.value = Idle

            } catch (e: Exception) {
                e.printStackTrace()
                val errorMsg = when (e) {
                    is UnknownHostException, is SocketTimeoutException, is NetworkErrorException -> "Cannot connect to the server, please check your internet connection and try again"
                    is CancellationException -> "Request cancelled"
                    else -> "Unknown error"
                }

                _state.value = Failure(errorMsg)
            }
        }
    }

    private suspend fun fetchPokemonsDetails(resList: List<NamedApiResource>): List<Pokemon> {
        return runBlocking {
            return@runBlocking try {
                val tasks = mutableListOf<Deferred<Pokemon>>()
                resList.forEach { tasks.add(async { client.getPokemon(it.id) }) }
                tasks.awaitAll()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }


    fun setIdleState() {
        _state.value = Idle
    }

    sealed interface HomeState {
        object InitialLoading : HomeState
        object Idle : HomeState
        object LoadingMore : HomeState
        class Failure(val msg: String) : HomeState
    }

}