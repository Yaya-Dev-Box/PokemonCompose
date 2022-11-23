package com.yayarh.pokemoncompose

import me.sargunvohra.lib.pokekotlin.model.*

object Utils {

    val dummyPokemon = Pokemon(
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

}