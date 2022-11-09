package com.yayarh.pokemoncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yayarh.pokemoncompose.presentation.home.HomeScreen
import com.yayarh.pokemoncompose.presentation.random.RandomScreen
import com.yayarh.pokemoncompose.presentation.search.SearchScreen
import com.yayarh.pokemoncompose.ui.theme.PokemonComposeTheme

class MainActivity : ComponentActivity() {

    // Full catalog tab, search tab, random tab

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokemonComposeTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNav(navController = navController) },
                    content = { Box(modifier = Modifier.padding(it)) { NavigationGraph(navController = navController) } }
                )
            }
        }
    }

    @Composable
    fun BottomNav(navController: NavController) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Search,
            BottomNavItem.Random
        )


        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->

                BottomNavigationItem(
                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                    label = { Text(text = item.title, fontSize = 9.sp) },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) { saveState = true }
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
            composable(BottomNavItem.Home.screen_route) { HomeScreen() }
            composable(BottomNavItem.Search.screen_route) { SearchScreen() }
            composable(BottomNavItem.Random.screen_route) { RandomScreen() }
        }
    }

    sealed class BottomNavItem(val title: String, val icon: Int, val screen_route: String) {

        object Home : BottomNavItem("Home", R.drawable.ic_baseline_menu_book_24, "home")
        object Search : BottomNavItem("Search", R.drawable.ic_baseline_search_24, "search")
        object Random : BottomNavItem("Random", R.drawable.ic_baseline_restart_alt_24, "random")
    }


}