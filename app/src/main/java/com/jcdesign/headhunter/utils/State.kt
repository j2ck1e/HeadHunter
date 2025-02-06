package com.jcdesign.headhunter.utils

import com.jcdesign.headhunter.domain.models.Offer
import com.jcdesign.headhunter.domain.models.Vacancy

sealed class State {
    object Loading : State()
    data class Success(
        val vacancies: List<Vacancy>,
        val offers: List<Offer>
    ) : State()
    data class Error(val message: String) : State()
}