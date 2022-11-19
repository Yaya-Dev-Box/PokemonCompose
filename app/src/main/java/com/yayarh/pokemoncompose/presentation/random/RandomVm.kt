package com.yayarh.pokemoncompose.presentation.random

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yayarh.pokemoncompose.presentation.random.RandomVm.RandomState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.sargunvohra.lib.pokekotlin.client.PokeApi
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RandomVm @Inject constructor(private val client: PokeApi) : ViewModel() {

    companion object {
        const val MAXIMUM_NUMBER_OF_POKEMONS = 904
    }

    private val _state = MutableStateFlow<RandomState>(Idle)
    val state = _state.asStateFlow()

    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon = _pokemon.asStateFlow()

    init {
        getRandomPokemon()
    }

    fun getRandomPokemon() {
        if (state.value is Loading) return

        val randomId = (1..MAXIMUM_NUMBER_OF_POKEMONS).random(Random(System.currentTimeMillis()))
        _pokemon.value = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = Loading
                delay(1000)
                _pokemon.value = client.getPokemon(randomId)
                _state.value = Idle

                println(pokemon.value)

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

    sealed interface RandomState {
        object Loading : RandomState
        object Idle : RandomState
        class Failure(val msg: String) : RandomState
    }

}