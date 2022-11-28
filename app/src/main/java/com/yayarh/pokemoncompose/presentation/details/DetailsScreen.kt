package com.yayarh.pokemoncompose.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.yayarh.pokemoncompose.R
import com.yayarh.pokemoncompose.presentation.details.DetailsVm.DetailsState.*
import com.yayarh.pokemoncompose.presentation.details.composables.PokeDetailsScreen
import com.yayarh.pokemoncompose.ui.theme.primaryYellow


@Destination
@Composable
fun DetailsScreen(vm: DetailsVm = hiltViewModel(), pokemonId: Int) {


    Box(modifier = Modifier.fillMaxSize()) {
        val state = vm.state.collectAsState().value

        if (state is PokemonLoaded) PokeDetailsScreen(pokemon = state.pokemon)

        if (state is Failure) Text(text = state.msg, modifier = Modifier.align(Alignment.Center), textAlign = TextAlign.Center)

        if (state is Loading) {
            CircularProgressIndicator(modifier = Modifier
                .align(Alignment.Center)
                .semantics { contentDescription = "Loading" })
        }

        if (state is Failure) {
            FloatingActionButton(
                onClick = vm::getPokemonDetails,
                backgroundColor = primaryYellow,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_restart_alt_24), contentDescription = "Reload")
            }
        }

    }
}