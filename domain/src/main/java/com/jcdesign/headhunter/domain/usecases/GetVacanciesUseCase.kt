package com.jcdesign.headhunter.domain.usecases

import com.jcdesign.headhunter.domain.models.Vacancies
import com.jcdesign.headhunter.domain.repository.AppRepository


class GetVacanciesUseCase (
    private val repository: AppRepository
) {

    suspend fun execute(): Vacancies {
        return repository.getVacancies()
    }
}