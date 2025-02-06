package com.jcdesign.headhunter.domain.usecases

import com.jcdesign.headhunter.domain.models.Vacancies
import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.domain.repository.AppRepository

class SearchVacancyByKeyWordUseCase(
    private val repository: AppRepository
) {

    suspend fun execute(keyWord: String): Vacancies {
        return repository.searchVacancyByKeyWord(keyWord)
    }
}