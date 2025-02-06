package com.jcdesign.headhunter.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jcdesign.headhunter.navigation.screens.Screen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    findScreenContent: @Composable () -> Unit,
    detailVacancyScreenContent: @Composable (description: String) -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    letterScreenContent: @Composable () -> Unit,
    messageScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Find
    ) {
        composable<Screen.Find> {
            findScreenContent()
        }
        composable<Screen.DetailVacancy> {
            val args = it.toRoute<Screen.DetailVacancy>()
            detailVacancyScreenContent(args.description)
        }
        composable<Screen.Favorite> {
            favoriteScreenContent()
        }
        composable<Screen.Letter> {
            letterScreenContent()
        }
        composable<Screen.Message> {
            messageScreenContent()
        }
        composable<Screen.Profile> {
            profileScreenContent()
        }
    }
}