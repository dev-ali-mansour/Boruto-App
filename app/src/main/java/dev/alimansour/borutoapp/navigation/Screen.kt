package dev.alimansour.borutoapp.navigation

import dev.alimansour.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY

sealed class Screen(val route: String) {

    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{$DETAILS_ARGUMENT_KEY}") {
        fun passHero(heroId: Int): String {
            return "details_screen/$heroId"
        }
    }

    object Search : Screen("search_screen")
}