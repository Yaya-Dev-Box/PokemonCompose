package com.yayarh.pokemoncompose.presentation.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yayarh.pokemoncompose.R
import com.yayarh.pokemoncompose.presentation.Extensions.getFormattedHeight
import com.yayarh.pokemoncompose.presentation.Extensions.getFormattedWeight
import com.yayarh.pokemoncompose.presentation.random.RandomVm.RandomState
import com.yayarh.pokemoncompose.ui.theme.primaryYellow
import me.sargunvohra.lib.pokekotlin.model.*


@Composable
fun RandomScreen(vm: RandomVm = hiltViewModel()) {

    Box(modifier = Modifier.fillMaxSize()) {
        val state = vm.state.collectAsState().value

        if (state is RandomState.PokemonLoaded) PokeDetailsScreen(pokemon = state.pokemon)

        if (state is RandomState.Failure) Text(text = state.msg, modifier = Modifier.align(Alignment.Center), textAlign = TextAlign.Center)

        if (state is RandomState.Loading) {
            CircularProgressIndicator(modifier = Modifier
                .align(Alignment.Center)
                .semantics { contentDescription = "Loading" })
        }

        if (state is RandomState.PokemonLoaded || state is RandomState.Failure) {
            FloatingActionButton(
                onClick = vm::getRandomPokemon,
                backgroundColor = primaryYellow,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_baseline_restart_alt_24), contentDescription = "Random")
            }
        }

    }
}

@Composable
fun PokeDetailsScreen(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Text(
            text = "Random",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )

        Text(
            text = pokemon.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )

        AsyncImage(
            model = pokemon.sprites.frontDefault,
            contentDescription = null,
            placeholder = if (LocalInspectionMode.current) painterResource(R.drawable.pikachuu_sprite) else null,
            modifier = Modifier.size(256.dp)
        )

        Text(text = pokemon.getFormattedWeight(LocalContext.current))
        Text(text = pokemon.getFormattedHeight(LocalContext.current))

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Stats:", fontWeight = FontWeight.Bold)
        pokemon.stats.forEach { pokeStat ->
            Text(text = pokeStat.stat.name.replaceFirstChar(Char::titlecase) + ": " + pokeStat.baseStat)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Abilities:", fontWeight = FontWeight.Bold)
        pokemon.abilities.forEach { pokeAbility ->
            Text(text = pokeAbility.ability.name.replaceFirstChar(Char::titlecase))
        }
    }
}

@Preview
@Composable
fun PokeDetailsPreview() {
    val previewPokemon = Pokemon(
        id = 1,
        name = "Buu",
        moves = emptyList(),
        baseExperience = 50,
        gameIndices = emptyList(),
        height = 50,
        weight = 70,
        isDefault = true,
        order = 2,
        species = NamedApiResource(name = "Pokemon", category = "None", id = 5),
        heldItems = emptyList(),
        forms = emptyList(),
        sprites = PokemonSprites(
            null,
            null,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            null,
            null,
            null,
            null,
            null
        ),
        stats = listOf(
            PokemonStat(stat = NamedApiResource(name = "Health", id = 5, category = "stat"), effort = 1, baseStat = 40),
            PokemonStat(stat = NamedApiResource(name = "Attack", id = 6, category = "stat"), effort = 1, baseStat = 10),
            PokemonStat(stat = NamedApiResource(name = "Defense", id = 7, category = "stat"), effort = 1, baseStat = 7)
        ),
        abilities = listOf(
            PokemonAbility(false, 1, NamedApiResource(name = "Chidori", id = 1, category = "ability")),
            PokemonAbility(false, 2, NamedApiResource(name = "Thunderstorm", id = 2, category = "ability")),
            PokemonAbility(false, 3, NamedApiResource(name = "Fast boi", id = 3, category = "ability"))
        ),
        types = emptyList()
    )

    PokeDetailsScreen(pokemon = previewPokemon)
}