package com.yayarh.pokemoncompose.presentation.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.yayarh.pokemoncompose.presentation.Extensions.getFormattedHeight
import com.yayarh.pokemoncompose.presentation.Extensions.getFormattedWeight
import com.yayarh.pokemoncompose.presentation.random.RandomVm.RandomState
import me.sargunvohra.lib.pokekotlin.model.Pokemon


@Composable
fun RandomScreen(vm: RandomVm = viewModel()) {
    val state = vm.state.collectAsState().value
    val pokemon by vm.pokemon.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        if (state is RandomState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        PokeDetailsScreen(pokemon = pokemon)

        if (state !is RandomState.Loading) {
            FloatingActionButton(
                onClick = vm::getRandomPokemon,
                backgroundColor = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(text = "Reload")
            }
        }

    }
}

@Composable
fun PokeDetailsScreen(pokemon: Pokemon?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Text(
            text = "Random",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )

        pokemon?.let {
            Text(
                text = it.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )

            Image(
                painter = rememberAsyncImagePainter(model = it.sprites.frontDefault),
                contentDescription = null,
                modifier = Modifier.size(256.dp)
            )

            Text(text = it.getFormattedWeight(LocalContext.current))
            Text(text = it.getFormattedHeight(LocalContext.current))

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Stats:", fontWeight = FontWeight.Bold)
            it.stats.forEach { pokeStat ->
                Text(text = pokeStat.stat.name.replaceFirstChar(Char::titlecase) + ": " + pokeStat.baseStat)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Abilities:", fontWeight = FontWeight.Bold)
            it.abilities.forEach { pokeAbility ->
                Text(text = pokeAbility.ability.name.replaceFirstChar(Char::titlecase))
            }

        }
    }
}