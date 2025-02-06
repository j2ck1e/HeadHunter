package com.jcdesign.headhunter.domain.usecases

import com.jcdesign.headhunter.domain.models.Vacancies
import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteVacanciesUseCase(
    private val repository: AppRepository
) {

    fun execute(): Flow<List<Vacancy>> {
        return repository.getFavoriteVacancies()
    }
}