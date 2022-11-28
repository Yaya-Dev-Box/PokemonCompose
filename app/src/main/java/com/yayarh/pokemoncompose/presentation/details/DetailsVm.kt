package com.yayarh.pokemoncompose.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yayarh.pokemoncompose.presentation.details.DetailsVm.DetailsState.*
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


@HiltViewModel
class DetailsVm @Inject constructor(private val client: PokeApi, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _state = MutableStateFlow<DetailsState>(InitialState)
    val state = _state.asStateFlow()

    init {
        getPokemonDetails()
    }

    fun getPokemonDetails() {
        if (state.value is Loading) return
        _state.value = Loading


        viewModelScope.launch(Dispatchers.IO) {
            try {
                val id = savedStateHandle.get<Int>("pokemonId") ?: throw Exception("No Pokemon selected")
                val pokemon = client.getPokemon(id)
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

    sealed interface DetailsState {
        object InitialState : DetailsState
        class PokemonLoaded(val pokemon: Pokemon) : DetailsState
        object Loading : DetailsState
        class Failure(val msg: String) : DetailsState
    }


}