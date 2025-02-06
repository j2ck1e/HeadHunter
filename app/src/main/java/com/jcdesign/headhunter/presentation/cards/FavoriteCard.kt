@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.jcdesign.headhunter.presentation.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jcdesign.headhunter.R
import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.presentation.MainViewModel
import com.jcdesign.headhunter.ui.fonts.mediumFontFamily
import com.jcdesign.headhunter.ui.fonts.regularFontFamily
import com.jcdesign.headhunter.ui.fonts.semiFontFamily
import com.jcdesign.headhunter.ui.theme.Blue
import com.jcdesign.headhunter.ui.theme.Green
import com.jcdesign.headhunter.ui.theme.Grey1
import com.jcdesign.headhunter.ui.theme.Grey3
import com.jcdesign.headhunter.ui.theme.White

@Preview(showBackground = true)
@Composable
fun FavoriteCard(
    viewModel: MainViewModel,
    vacancy: Vacancy,
    onCardClick: (Vacancy) -> Unit = {},
    onButtonClick: () -> Unit = {}
) {
    val isFavorite = remember() {
        mutableStateOf(true)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCardClick(vacancy)
            },
        colors = CardDefaults.cardColors(Grey1)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    color = White,
                    fontFamily = mediumFontFamily,
                    fontSize = 16.sp,
                    text = vacancy.title
                )
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            viewModel.removeVacancyFromFavorite(vacancy)
                        },
                    tint = Blue,
                    painter = painterResource(
                        id = if (isFavorite.value) {
                            R.drawable.active_heart
                        } else {
                            R.drawable.default_heart
                        }
                    ),
                    contentDescription = "heart"
                )
            }

            Text(
                color = White,
                fontFamily = semiFontFamily,
                fontSize = 20.sp,
                text = vacancy.salary
            )
            Text(
                color = White,
                fontFamily = regularFontFamily,
                fontSize = 14.sp,
                text = vacancy.town
            )
            Row() {
                Text(
                    color = White,
                    fontFamily = regularFontFamily,
                    fontSize = 14.sp,
                    text = vacancy.company
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    modifier = Modifier.size(16.dp),
                    tint = White,
                    painter = painterResource(id = R.drawable.check_company),
                    contentDescription = "heart"
                )

            }
            Row() {
                Icon(
                    modifier = Modifier.size(16.dp),
                    tint = White,
                    painter = painterResource(id = R.drawable.experience),
                    contentDescription = "heart"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    color = White,
                    fontFamily = regularFontFamily,
                    fontSize = 14.sp,
                    text = vacancy.previewExperienceText
                )
            }
            Text(
                color = Grey3,
                fontFamily = regularFontFamily,
                fontSize = 14.sp,
                text = vacancy.publishedDate
            )
            Button(colors = ButtonDefaults.buttonColors(Green),
                onClick = {
                    onButtonClick()
                }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = Center,
                    color = White,
                    fontFamily = regularFontFamily,
                    fontSize = 16.sp,
                    text = "Откликнуться"
                )
            }
        }
    }
}