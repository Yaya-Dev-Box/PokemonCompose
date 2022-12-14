package com.yayarh.pokemoncompose.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.yayarh.pokemoncompose.presentation.Extensions.getFormattedHeight
import com.yayarh.pokemoncompose.presentation.Extensions.getFormattedWeight
import com.yayarh.pokemoncompose.presentation.destinations.DetailsScreenDestination
import com.yayarh.pokemoncompose.presentation.home.HomeVm.HomeState
import com.yayarh.pokemoncompose.ui.theme.primaryYellowVariant
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import me.sargunvohra.lib.pokekotlin.model.PokemonSprites

@Destination(start = true)
@Composable
fun HomeScreen(vm: HomeVm = hiltViewModel(), navigator: NavController) {
    val state = vm.state.collectAsState().value
    val pokemonList by vm.pokemonList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Center)
    ) {
        Text(
            text = "Catalog",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )

        Box {
            if (state is HomeState.InitialLoading) CircularProgressIndicator(modifier = Modifier.align(Center))

            /* If we are refreshing the list while it is not empty, that means the refresh was triggered by the user */
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state is HomeState.InitialLoading && pokemonList.isNotEmpty()),
                onRefresh = { vm.loadPokemonList(true) }) {

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(pokemonList) { index, pokemon ->
                        if (index == pokemonList.lastIndex) vm.loadPokemonList(false)
                        PokemonItem(pokemon = pokemon) { id -> navigator.navigate(DetailsScreenDestination(pokemonId = id)) }
                    }
                }
            }

            if (state is HomeState.Failure) {
                Toast.makeText(LocalContext.current, state.msg, Toast.LENGTH_SHORT).show()
                vm.setIdleState()
            }

            if (state is HomeState.LoadingMore) Text(
                text = "Loading more...",
                Modifier
                    .align(BottomCenter)
                    .fillMaxWidth()
                    .background(primaryYellowVariant)
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: (pokemonId: Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(pokemon.id) },
        elevation = 10.dp
    ) {
        Row {
            AsyncImage(
                model = pokemon.sprites.frontDefault,
                contentDescription = null,
                placeholder = if (LocalInspectionMode.current) painterResource(com.yayarh.pokemoncompose.R.drawable.pikachuu_sprite) else null,
                modifier = Modifier.size(128.dp)
            )
            Column {
                Text(text = pokemon.name.replaceFirstChar(Char::titlecase))
                Text(text = pokemon.getFormattedWeight(LocalContext.current))
                Text(text = pokemon.getFormattedHeight(LocalContext.current))
            }
        }
    }
}

@Preview
@Composable
fun PokemonItemPreview() {
    val previewPokemon = Pokemon(
        id = 1,
        name = "Buu",
        abilities = emptyList(),
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
        stats = emptyList(),
        moves = emptyList(),
        types = emptyList()
    )

    PokemonItem(pokemon = previewPokemon) {}
}
