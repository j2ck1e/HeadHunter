package com.jcdesign.headhunter.domain.usecases

import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.domain.repository.AppRepository

class DeleteVacancyFromFavoriteUseCase(
    private val repository: AppRepository
) {
    suspend fun execute(vacancy: Vacancy): Boolean {
        return repository.removeVacancyFromFavorite(vacancy)
    }
}