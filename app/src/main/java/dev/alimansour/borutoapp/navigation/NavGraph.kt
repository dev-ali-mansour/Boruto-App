package dev.alimansour.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.alimansour.borutoapp.presentation.screen.home.HomeScreen
import dev.alimansour.borutoapp.presentation.screen.search.SearchScreen
import dev.alimansour.borutoapp.presentation.screen.splash.SplashScreen
import dev.alimansour.borutoapp.presentation.screen.welcome.WelcomeScreen
import dev.alimansour.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(name = DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {

        }
        composable(route = Screen.Search.route) {
            SearchScreen()
        }
    }
}