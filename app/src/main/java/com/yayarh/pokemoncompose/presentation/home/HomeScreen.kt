package com.yayarh.pokemoncompose.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import me.sargunvohra.lib.pokekotlin.model.Pokemon

@Composable
fun HomeScreen(vm: HomeVm = viewModel()) {
    val state = vm.state.collectAsState().value
    val pokemonList = vm.pokemonList.collectAsState().value

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
            fontSize = 20.sp
        )

        Box {
            if (state is HomeVm.HomeState.InitialLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(pokemonList) { PokemonItem(pokemon = it) }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { },
        backgroundColor = Color.LightGray,
        elevation = 10.dp
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(pokemon.sprites.frontDefault),
                contentDescription = null,
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