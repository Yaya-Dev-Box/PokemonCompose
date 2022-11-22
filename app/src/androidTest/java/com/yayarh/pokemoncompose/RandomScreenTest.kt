package com.yayarh.pokemoncompose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.yayarh.pokemoncompose.presentation.random.RandomScreen
import com.yayarh.pokemoncompose.presentation.random.RandomVm
import com.yayarh.pokemoncompose.ui.theme.PokemonComposeTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.*
import me.sargunvohra.lib.pokekotlin.client.PokeApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
@HiltAndroidTest
class RandomScreenTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeTestRule = createComposeRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Inject lateinit var testingClient: PokeApi

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.setContent {
            PokemonComposeTheme {
                RandomScreen(vm = RandomVm(testingClient))
            }
        }
    }

    @Test
    fun testNodesVisibility(): Unit = runBlocking {
        launch(mainThreadSurrogate) {
            repeat(100) {
                composeTestRule.onNodeWithContentDescription("Random").assertIsDisplayed().performClick()
                composeTestRule.onNodeWithText("Buu").assertExists().assertIsDisplayed()
            }
        }
    }


}