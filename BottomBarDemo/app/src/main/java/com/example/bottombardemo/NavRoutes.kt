package com.example.bottombardemo

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes("home")
    data object Contacts : NavRoutes("contacts")
    data object Favorites : NavRoutes("favorites")
}