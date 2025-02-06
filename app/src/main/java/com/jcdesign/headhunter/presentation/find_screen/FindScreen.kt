package com.jcdesign.headhunter.presentation.find_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jcdesign.headhunter.R
import com.jcdesign.headhunter.domain.models.Offer
import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.navigation.screens.Screen
import com.jcdesign.headhunter.navigation.state.NavigationState
import com.jcdesign.headhunter.presentation.MainViewModel
import com.jcdesign.headhunter.presentation.cards.OfferCard
import com.jcdesign.headhunter.presentation.cards.VacancyCard
import com.jcdesign.headhunter.ui.fonts.mediumFontFamily
import com.jcdesign.headhunter.ui.fonts.semiFontFamily
import com.jcdesign.headhunter.ui.theme.Blue
import com.jcdesign.headhunter.ui.theme.White


@Composable
fun FindScreen(
    viewModel: MainViewModel = hiltViewModel<MainViewModel>(),
    paddingValues: PaddingValues,
    offerList: List<Offer>,
    vacancyList: List<Vacancy>,
    navigationState: NavigationState
) {

    val context = LocalContext.current

    val isAllVacancy = rememberSaveable {
        mutableStateOf(false)
    }

    if(vacancyList.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (!isAllVacancy.value) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                )
                {

                    OutlinedTextField(
                        modifier = Modifier
                            .weight(1f),
                        value = "",
                        maxLines = 1,
                        placeholder = {
                            Text(
                                textAlign = TextAlign.Start,
                                text = "Должность, ключевые слова",
                                fontSize = 12.sp
                            )
                        },
                        onValueChange = { },
                        enabled = false,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.find_default),
                                contentDescription = null
                            )
                        }
                    )

                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(R.drawable.filter_button),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))


                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(offerList) { offer ->
                        OfferCard(offer) {
                            viewModel.onOfferClick(it, context)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Вакансии для вас",
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontFamily = semiFontFamily
                )
                Spacer(modifier = Modifier.height(16.dp))
            } else {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    )
                    {

                        OutlinedTextField(modifier = Modifier
                            .weight(1f),
                            value = "",
                            placeholder = {
                                Text(
                                    textAlign = TextAlign.Start,
                                    text = "Должность по подходящим ...",
                                    fontSize = 12.sp,

                                    )
                            },
                            maxLines = 1,
                            onValueChange = { },
                            enabled = false,
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.arrow_text_field),
                                    contentDescription = null,
                                    modifier = Modifier.clickable {
                                        isAllVacancy.value = !isAllVacancy.value
                                    }
                                )
                            }
                        )
                        Icon(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(R.drawable.filter_button),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(
                            fontFamily = mediumFontFamily,
                            fontSize = 14.sp,
                            text = pluralizeVacancy(vacancyList.size))
                        Text(
                            text = "По соответсвию",
                            color = Blue
                        )
                    }
                }
                Spacer(modifier = Modifier.size(25.dp))
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                if (isAllVacancy.value) {
                    items(vacancyList) { vacancy ->
                        VacancyCard(
                            viewModel,
                            vacancy,
                            onCardClick = {
                                navigationState.navHostController.navigate(Screen.DetailVacancy(vacancy.description))
                            })
                    }
                } else {

                    items(vacancyList.take(3)) { vacancy ->
                        VacancyCard(
                            viewModel,
                            vacancy,
                            onCardClick = {
                                navigationState.navHostController.navigate(Screen.DetailVacancy(it.description))
                            })
                    }
                    item {
                        if (!isAllVacancy.value) {
                            Button(modifier = Modifier,
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(Blue),
                                onClick = {
                                    isAllVacancy.value = !isAllVacancy.value
                                }) {
                                Text(
                                    fontFamily = semiFontFamily,
                                    fontSize = 16.sp,
                                    color = White,
                                    text = "Еще ${pluralizeVacancy(vacancyList.size - 3)}",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
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