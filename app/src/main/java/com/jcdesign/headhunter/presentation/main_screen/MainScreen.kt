package com.jcdesign.headhunter.presentation.main_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jcdesign.headhunter.R
import com.jcdesign.headhunter.domain.models.Offer
import com.jcdesign.headhunter.domain.models.Offers
import com.jcdesign.headhunter.domain.models.Vacancies
import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.navigation.bottom_items.NavigationItem
import com.jcdesign.headhunter.navigation.graph.AppNavGraph
import com.jcdesign.headhunter.navigation.state.NavigationState
import com.jcdesign.headhunter.navigation.state.rememberNavigationState
import com.jcdesign.headhunter.presentation.App
import com.jcdesign.headhunter.presentation.detail_screen.DetailScreen
import com.jcdesign.headhunter.presentation.favorite_screen.FavoritesScreen
import com.jcdesign.headhunter.presentation.find_screen.FindScreen
import com.jcdesign.headhunter.ui.fonts.regularFontFamily
import com.jcdesign.headhunter.ui.theme.Blue
import com.jcdesign.headhunter.ui.theme.Grey4
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun MainScreen(
    offerList: List<Offer>,
    vacancyList: List<Vacancy>
) {
    val navigationState: NavigationState = rememberNavigationState()

    Scaffold(modifier = Modifier
        .fillMaxWidth(),
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val items = listOf(
                    NavigationItem.Find,
                    NavigationItem.Favorite,
                    NavigationItem.Letter,
                    NavigationItem.Message,
                    NavigationItem.Profile
                )
                items.forEach { destination ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(destination.screen::class) }
                                ==
                                true,
                        onClick = {
                            navigationState.navigateTo(destination.screen)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(destination.icon),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(destination.titleResId),
                                fontFamily = regularFontFamily,
                                fontSize = 10.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Blue,
                            selectedTextColor = Blue,
                            unselectedIconColor = Grey4,
                            unselectedTextColor = Grey4
                        )
                    )
                }
            }
        }

    ) { innerPadding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            findScreenContent = {
                FindScreen(
                    paddingValues = innerPadding,
                    offerList = offerList,
                    vacancyList = vacancyList,
                    navigationState = navigationState
                )
            },
            detailVacancyScreenContent = { DetailScreen(it) },
            favoriteScreenContent = { FavoritesScreen(
                paddingValues = innerPadding,
                vacancyList = vacancyList,
                navigationState = navigationState
            ) },
            letterScreenContent = { Text(text = stringResource(R.string.letter_string)) },
            messageScreenContent = { Text(text = stringResource(R.string.message_string)) },
            profileScreenContent = { Text(text = stringResource(R.string.profile_string)) }
        )
    }
}



