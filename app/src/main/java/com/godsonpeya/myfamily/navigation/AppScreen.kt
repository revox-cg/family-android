package com.godsonpeya.myfamily.navigation

sealed class AppScreen(val route: String) {

    data object Home : AppScreen("home")

    data object Detail : AppScreen("detail/{memberId}") {
        fun createRoute(memberId: String) = "detail/$memberId"
    }


}