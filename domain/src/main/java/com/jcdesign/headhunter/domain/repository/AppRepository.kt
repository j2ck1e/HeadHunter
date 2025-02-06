package com.jcdesign.headhunter.domain.repository

import com.jcdesign.headhunter.domain.models.Offers
import com.jcdesign.headhunter.domain.models.Vacancies
import com.jcdesign.headhunter.domain.models.Vacancy
import kotlinx.coroutines.flow.Flow


interface AppRepository {

    suspend fun getVacancies() : Vacancies

    suspend fun getOffers() : Offers

    suspend fun getVacancyById(id: String): Vacancy

    suspend fun searchVacancyByKeyWord(keyWord: String): Vacancies

    fun getFavoriteVacancies() : Flow<List<Vacancy>>

    suspend fun addVacancyToFavorite(vacancy: Vacancy): Boolean

    suspend fun removeVacancyFromFavorite(vacancy: Vacancy): Boolean

}