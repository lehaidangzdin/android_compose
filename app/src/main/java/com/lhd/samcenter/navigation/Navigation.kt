package com.lhd.samcenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lhd.samcenter.presention.home.HomeScreen
import com.lhd.samcenter.presention.home.HomeViewModel

sealed class Screens(val route: String) {
    object Home : Screens("home_screen")
    object Detail : Screens("detail_screen")
}

@Composable
fun NavigationAppHost() {
    val nav = rememberNavController()
    NavHost(
        nav, "home_screen",
    ) {
        composable(Screens.Home.route) { HomeScreen(nav) }
//        composable(Screens.Detail.route) { DetailScreen(nav) }
    }
}