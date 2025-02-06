package com.jcdesign.headhunter.domain.usecases

import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.domain.repository.AppRepository

class AddVacancyToFavoriteUseCase(
    private val repository: AppRepository
) {

    suspend fun execute(vacancy: Vacancy): Boolean {
        return repository.addVacancyToFavorite(vacancy)
    }
}