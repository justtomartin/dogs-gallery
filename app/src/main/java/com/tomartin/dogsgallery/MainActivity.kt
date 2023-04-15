package com.tomartin.dogsgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tomartin.dogsgallery.ui.navigation.NavigationDestinations
import com.tomartin.dogsgallery.ui.screens.BreedsListScreen
import com.tomartin.dogsgallery.ui.screens.RandomScreen
import com.tomartin.dogsgallery.ui.screens.SearchScreen
import com.tomartin.dogsgallery.ui.theme.DogsGalleryTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            DogsGalleryTheme {

                val destinations = listOf(
                    NavigationDestinations.BreedsList,
                    NavigationDestinations.Search,
                    NavigationDestinations.Random
                )

                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = NavigationBarDefaults.containerColor,
                            content = {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                destinations.forEach { screen ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                imageVector = screen.icon,
                                                contentDescription = null
                                            )
                                        },
                                        label = { Text(text = stringResource(screen.resourceId), style = MaterialTheme.typography.labelMedium) },
                                        selected =  currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.background,
                    content = { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = NavigationDestinations.BreedsList.route,
                            modifier = Modifier.padding(paddingValues),
                            builder = {
                                composable(route = NavigationDestinations.BreedsList.route, content = { BreedsListScreen() })
                                composable(route = NavigationDestinations.Search.route, content = { SearchScreen() })
                                composable(route = NavigationDestinations.Random.route, content = { RandomScreen() })
                            }
                        )
                    }
                )
            }
        }
    }
}