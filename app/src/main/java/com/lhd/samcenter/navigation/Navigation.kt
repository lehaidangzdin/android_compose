package com.lhd.samcenter.navigation

import HomeScreenV2
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lhd.samcenter.presention.detail.DetailScreen


sealed class Screens(val route: String) {
    object Home : Screens("home_screen")
    object Detail : Screens("detail_screen")
}

@Composable
fun NavigationAppHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Screens.Home.route,
    ) {
        composable(route = Screens.Home.route) { HomeScreenV2(navController) }
        composable(Screens.Detail.route) { DetailScreen(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun SlideScreenPreview() {
    MaterialTheme {
        NavigationAppHost()
    }
}
