package com.tomartin.dogsgallery.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.tomartin.dogsgallery.R

sealed class NavigationDestinations(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object BreedsList: NavigationDestinations("breeds_list", R.string.breeds_list, Icons.Default.Pets)
    object Search: NavigationDestinations("search", R.string.search, Icons.Default.Search)
    object Random: NavigationDestinations("random", R.string.random, Icons.Default.Casino)
}
