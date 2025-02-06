package com.jcdesign.headhunter.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.jcdesign.headhunter.data.network.models.ResponseDto
import com.jcdesign.headhunter.data.room.models.VacancyEntity
import com.jcdesign.headhunter.domain.models.Offer
import com.jcdesign.headhunter.domain.models.Offers
import com.jcdesign.headhunter.domain.models.Vacancies
import com.jcdesign.headhunter.domain.models.Vacancy
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Mapper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun mapResponseDtoToVacancies(responseDto: ResponseDto): Vacancies {
        val result = mutableListOf<Vacancy>()
        val vacancies = responseDto.vacancies
        for (item in vacancies) {
            val vacancy = Vacancy(
                id = item.id,
                lookingNumber = if(item.lookingNumber != 0) pluralizePerson(item.lookingNumber) else "",
                isFavorite = item.isFavorite,
                title = item.title,
                salary = item.salaryDto.full,
                town = item.addressDto.town,
                company = item.company,
                previewExperienceText = item.experienceDto.previewText,
                publishedDate = formatPublishedDate(item.publishedDate),
                description = item.description
            )
            result.add(vacancy)

        }
        return Vacancies(result)
    }

    fun mapResponseDtoToOffers(responseDto: ResponseDto): Offers {
        val result = mutableListOf<Offer>()

        val offers = responseDto.offers
        for (item in offers) {
            val offer = Offer(
                id = item.offerId,
                title = item.offerTitle.trim(),
                textButton = item.offerButton.text,
                link = item.offerLink
            )
            result.add(offer)
        }
        return Offers(result)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun mapEntityListToVacancy(entityList: List<VacancyEntity>): List<Vacancy> {
        val result = mutableListOf<Vacancy>()

        for (item in entityList) {
            val vacancy =
                Vacancy(
                    id = item.id,
                    isFavorite = item.isFavorite,
                    title = item.title,
                    salary = item.salary,
                    town = item.town,
                    company = item.company,
                    previewExperienceText = item.previewExperienceText,
                    publishedDate = item.publishedDate
                )

            result.add(vacancy)
        }
        return result
    }

    fun mapVacancyToEntity(vacancy: Vacancy): VacancyEntity {
        return VacancyEntity(
            id = vacancy.id,
            title = vacancy.title,
            salary = vacancy.salary,
            town = vacancy.town,
            company = vacancy.company,
            previewExperienceText = vacancy.previewExperienceText,
            publishedDate = vacancy.publishedDate,
            isFavorite = vacancy.isFavorite
        )
    }


    private fun pluralizePerson(count: Int): String {
        val remainder100 = count % 100
        val remainder10 = count % 10

        return when {
            remainder100 in 11..14 -> "Сейчас просматривает $count человек"
            remainder10 == 1 -> "Сейчас просматривает $count человек"
            remainder10 in 2..4 -> "Сейчас просматривает $count человека"
            else -> "Сейчас просматривает $count человек"
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatPublishedDate(dateString: String): String {
        val date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
        val day = date.dayOfMonth
        val month = getMonthInGenitive(date.monthValue)

        return "Опубликовано $day $month"
    }

    private fun getMonthInGenitive(month: Int): String {
        return when (month) {
            1 -> "января"
            2 -> "февраля"
            3 -> "марта"
            4 -> "апреля"
            5 -> "мая"
            6 -> "июня"
            7 -> "июля"
            8 -> "августа"
            9 -> "сентября"
            10 -> "октября"
            11 -> "ноября"
            12 -> "декабря"
            else -> throw IllegalArgumentException("Некорректный номер месяца: $month")
        }
    }

}
