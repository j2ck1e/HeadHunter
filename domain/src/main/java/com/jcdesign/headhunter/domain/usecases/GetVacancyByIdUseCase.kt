package com.jcdesign.headhunter.domain.usecases

import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.domain.repository.AppRepository

class GetVacancyByIdUseCase(
    private val repository: AppRepository
) {

    suspend fun execute(id: String): Vacancy {
        return repository.getVacancyById(id)
    }
}