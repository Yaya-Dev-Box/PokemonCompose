package com.yayarh.pokemoncompose.presentation.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yayarh.pokemoncompose.presentation.random.RandomVm.RandomState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.sargunvohra.lib.pokekotlin.client.PokeApi
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import java.io.IOException
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RandomVm @Inject constructor(private val client: PokeApi) : ViewModel() {

    companion object {
        const val MAXIMUM_NUMBER_OF_POKEMONS = 904
    }

    private val _state = MutableStateFlow<RandomState>(InitialState)
    val state = _state.asStateFlow()

    init {
        getRandomPokemon()
    }

    fun getRandomPokemon() {
        if (state.value is Loading) return

        val randomId = (1..MAXIMUM_NUMBER_OF_POKEMONS).random(Random(System.currentTimeMillis()))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = Loading
                val pokemon = client.getPokemon(randomId)
                _state.value = PokemonLoaded(pokemon)

                println(pokemon)

            } catch (e: Exception) {
                e.printStackTrace()
                val errorMsg = when (e) {
                    is IOException -> "Cannot connect to the server, please check your internet connection and try again"
                    is CancellationException -> "Request cancelled"
                    else -> "Unknown error"
                }
                _state.value = Failure(errorMsg)
            }
        }
    }

    sealed interface RandomState {
        object InitialState : RandomState
        class PokemonLoaded(val pokemon: Pokemon) : RandomState
        object Loading : RandomState
        class Failure(val msg: String) : RandomState
    }

}