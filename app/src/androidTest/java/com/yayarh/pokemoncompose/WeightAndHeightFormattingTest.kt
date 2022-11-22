package com.yayarh.pokemoncompose

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.yayarh.pokemoncompose.presentation.Extensions.getFormattedHeight
import com.yayarh.pokemoncompose.presentation.Extensions.getFormattedWeight
import io.kotest.matchers.shouldBe
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import me.sargunvohra.lib.pokekotlin.model.PokemonSprites
import org.junit.Test

class WeightAndHeightFormattingTest {

    //TODO: Fix flaky tests

    private val ctx = ApplicationProvider.getApplicationContext<Context>()

    private val dummyPokemon = Pokemon(
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
        sprites = PokemonSprites(null, null, null, null, null, null, null, null),
        stats = emptyList(),
        moves = emptyList(),
        types = emptyList()
    )


    @Test
    fun weightFormattingIsCorrect() {
        val poke1 = dummyPokemon.copy(weight = 456)
        poke1.getFormattedWeight(ctx) shouldBe "Weight: " + "45.6" + " kilograms"

        val poke2 = dummyPokemon.copy(weight = 6)
        poke2.getFormattedWeight(ctx) shouldBe "Weight: " + "0.61" + " kilograms"

        val poke3 = dummyPokemon.copy(weight = 72)
        poke3.getFormattedWeight(ctx) shouldBe "Weight: " + "7.2" + " kilograms"

        val poke4 = dummyPokemon.copy(weight = 1500)
        poke4.getFormattedWeight(ctx) shouldBe "Weight: " + "150" + " kilograms"

        val poke5 = dummyPokemon.copy(weight = 1783)
        poke5.getFormattedWeight(ctx) shouldBe "Weight: " + "178.31" + " kilograms"
    }

    @Test
    fun heightFormattingIsCorrect() {
        val poke1 = dummyPokemon.copy(height = 456)
        poke1.getFormattedHeight(ctx) shouldBe "Height: " + "45.6" + " meters"

        val poke2 = dummyPokemon.copy(height = 6)
        poke2.getFormattedHeight(ctx) shouldBe "Height: " + "0.61" + " meters"

        val poke3 = dummyPokemon.copy(height = 72)
        poke3.getFormattedHeight(ctx) shouldBe "Height: " + "7.2" + " meters"

        val poke4 = dummyPokemon.copy(height = 1500)
        poke4.getFormattedHeight(ctx) shouldBe "Height: " + "150" + " meters"

        val poke5 = dummyPokemon.copy(height = 1783)
        poke5.getFormattedHeight(ctx) shouldBe "Height: " + "178.31" + " meters"

    }

}