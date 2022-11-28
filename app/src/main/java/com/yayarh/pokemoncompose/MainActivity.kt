package com.yayarh.pokemoncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import com.yayarh.pokemoncompose.presentation.NavGraphs
import com.yayarh.pokemoncompose.presentation.appCurrentDestinationAsState
import com.yayarh.pokemoncompose.presentation.destinations.HomeScreenDestination
import com.yayarh.pokemoncompose.presentation.destinations.RandomScreenDestination
import com.yayarh.pokemoncompose.presentation.destinations.SearchScreenDestination
import com.yayarh.pokemoncompose.ui.theme.PokemonComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokemonComposeTheme {
                val navController = rememberNavController()
                val currentDestination by navController.appCurrentDestinationAsState()
                val bottomBarVisibility = rememberSaveable { mutableStateOf(true) }

                bottomBarVisibility.value = when (currentDestination?.route) {
                    HomeScreenDestination.route, SearchScreenDestination.route, RandomScreenDestination.route -> true
                    else -> false
                }

                Scaffold(
                    bottomBar = { BottomBar(navController = navController, bottomBarVisibility.value) },
                    content = {
                        Box(modifier = Modifier.padding(it)) { DestinationsNavHost(navController = navController, navGraph = NavGraphs.root) }
                    }
                )
            }
        }
    }

    /**
     * @see <a href="https://github.com/raamcosta/compose-destinations/blob/main/sample/src/main/java/com/ramcosta/destinations/sample/ui/composables/BottomBar.kt">Sample</a>
     */
    @Composable
    fun BottomBar(navController: NavHostController, isVisible: Boolean) {
        if (!isVisible) return

        BottomNavigation {
            BottomNavItems.values().forEach { destination ->
                val isCurrentDestOnBackStack = navController.isRouteOnBackStack(destination.direction)
                BottomNavigationItem(
                    selected = isCurrentDestOnBackStack,
                    onClick = {
                        if (isCurrentDestOnBackStack) {
                            // When we click again on a bottom bar item and it was already selected
                            // we want to pop the back stack until the initial destination of this bottom bar item
                            navController.popBackStack(destination.direction, false)
                            return@BottomNavigationItem
                        }

                        navController.navigate(destination.direction) {
                            // Pop up to the root of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(NavGraphs.root) { saveState = true }

                            // Avoid multiple copies of the same destination when
                            // re-selecting the same item
                            launchSingleTop = true
                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    },
                    icon = { Icon(painterResource(destination.icon), contentDescription = destination.title) },
                    label = { Text(destination.title) },
                )
            }
        }
    }


    enum class BottomNavItems(val title: String, val icon: Int, val direction: DirectionDestinationSpec) {
        Home("Home", R.drawable.ic_baseline_menu_book_24, HomeScreenDestination),
        Search("Search", R.drawable.ic_baseline_search_24, SearchScreenDestination),
        Random("Random", R.drawable.ic_baseline_restart_alt_24, RandomScreenDestination)
    }


}