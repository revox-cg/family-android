package com.godsonpeya.myfamily.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.godsonpeya.myfamily.ui.screen.DetailScreen
import com.godsonpeya.myfamily.ui.screen.HomeScreen

@Composable
fun AppNavHost(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = AppScreen.Home.route) {
        composable(AppScreen.Home.route) {
            HomeScreen(navController = navHostController)
        }
        composable(AppScreen.Detail.route) { navBackStackEntry ->
            val memberId = navBackStackEntry.arguments?.getString("memberId")
            DetailScreen(navHostController = navHostController, memberId = memberId!!)
        }

    }
}