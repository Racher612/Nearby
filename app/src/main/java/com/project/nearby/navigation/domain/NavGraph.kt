package com.project.nearby.navigation.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.nearby.R
import com.project.nearby.event.pres.EventScreen
import com.project.nearby.main.pres.MainScreen
import com.project.nearby.navigation.domain.model.Routes
import com.project.nearby.retrofit.models.concert.Event

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val navId = stringResource(id = R.string.nav_id)
    val navigateToRoute: (String) -> Unit = {route ->
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Main.route
    ){
        composable(Routes.Main.route){
            MainScreen(navigateToRoute = navigateToRoute)
        }
        composable(
            route = Routes.Event.route + Routes.Id.route,
            arguments = listOf(navArgument(navId){ type = NavType.StringType})
        ){entry ->
            val id = entry.arguments?.getString(navId)
            EventScreen(id ?: "")
        }
    }
}