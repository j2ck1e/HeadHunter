package com.jcdesign.headhunter.presentation.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jcdesign.headhunter.R
import com.jcdesign.headhunter.domain.models.Offer
import com.jcdesign.headhunter.ui.fonts.mediumFontFamily
import com.jcdesign.headhunter.ui.fonts.regularFontFamily
import com.jcdesign.headhunter.ui.theme.Green
import com.jcdesign.headhunter.ui.theme.Grey1

@Composable
fun OfferCard(
    offer: Offer,
    onLinkClick: (String) -> Unit
) {

    val offerId = rememberSaveable() {
        mutableStateOf(offer.id)
    }

    Card(
        modifier = Modifier
            .size(width = 132.dp, height = 120.dp)
            .clickable {
                onLinkClick(offer.link)
            },
        colors = CardDefaults.cardColors(Grey1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            val image: Int? = when (offerId.value) {
                "level_up_resume" -> {
                    R.drawable.star_circle
                }

                "temporary_job" -> {
                    R.drawable.note_circle
                }

                "near_vacancies" -> {
                    R.drawable.blue_circle
                }

                else -> {null}
            }
            if(image != null) {
                Icon(
                    painter = painterResource(id = image),
                    contentDescription = "circle",
                    tint = Color.Unspecified
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = offer.title,
                fontFamily = mediumFontFamily,
                fontSize = 14.sp,
                maxLines = if (offer.id.isNotEmpty()) 2 else 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = offer.textButton,
                fontFamily = regularFontFamily,
                fontSize = 14.sp,
                color = Green
            )

        }
    }

}