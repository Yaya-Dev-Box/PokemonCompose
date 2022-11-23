package com.yayarh.pokemoncompose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.yayarh.pokemoncompose.presentation.random.RandomScreen
import com.yayarh.pokemoncompose.presentation.random.RandomVm
import com.yayarh.pokemoncompose.ui.theme.PokemonComposeTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.*
import me.sargunvohra.lib.pokekotlin.client.PokeApi
import me.sargunvohra.lib.pokekotlin.model.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
@HiltAndroidTest
class RandomScreenTest {

    @get:Rule val hiltRule = HiltAndroidRule(this)
    @get:Rule val composeTestRule = createComposeRule()
    @get:Rule val mockkRule = MockKRule(this)

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Inject lateinit var testingClient: PokeApi

    @Before
    fun setup() {
        hiltRule.inject()
        setupMocks()

        composeTestRule.setContent {
            PokemonComposeTheme {
                RandomScreen(vm = RandomVm(testingClient))
            }
        }

    }

    @Test
    fun testNodesVisibility(): Unit = runBlocking {
        launch(mainThreadSurrogate) {
            composeTestRule.onNodeWithText("Buu").assertExists().assertIsDisplayed()
            composeTestRule.onNodeWithContentDescription("Random").assertIsDisplayed().performClick()
        }
    }

    private fun setupMocks() {
        MockKAnnotations.init(this)
        every { testingClient.getPokemon(any()) } returns Utils.dummyPokemon
    }

}