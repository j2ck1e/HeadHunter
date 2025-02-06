package com.jcdesign.headhunter.presentation.favorite_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.navigation.state.NavigationState
import com.jcdesign.headhunter.presentation.MainViewModel
import com.jcdesign.headhunter.presentation.cards.FavoriteCard
import com.jcdesign.headhunter.ui.fonts.mediumFontFamily
import com.jcdesign.headhunter.ui.fonts.semiFontFamily

@Composable
fun FavoritesScreen(
    viewModel: MainViewModel = hiltViewModel<MainViewModel>(),
    paddingValues: PaddingValues,
    vacancyList: List<Vacancy>,
    navigationState: NavigationState
) {

    vacancyList.forEach {
        if (it.isFavorite) viewModel.addVacancyToFavorite(it)
    }
    viewModel.loadFavorites()
    Log.i("MyLog", "FavoritesScreen: loadFavorites")
    val favoriteVacancies = viewModel.favoriteVacancies.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Text(
            text = "Избранное",
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            fontSize = 20.sp,
            fontFamily = semiFontFamily
        )
        Text(
            fontFamily = mediumFontFamily,
            fontSize = 14.sp,
            text = pluralizeVacancy(favoriteVacancies.value.size))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favoriteVacancies.value) { vacancy ->
                Log.i("MyTag", "FavoritesScreen: $vacancy")
                FavoriteCard(
                    viewModel = viewModel,
                    vacancy = vacancy
                )
            }
        }
    }
}

private fun pluralizeVacancy(count: Int): String {
    val remainder = count % 10

    return when (remainder) {
        1 -> "$count вакансия"
        in 2..4 -> "$count вакансии"
        else -> "$count вакансий"
    }
}