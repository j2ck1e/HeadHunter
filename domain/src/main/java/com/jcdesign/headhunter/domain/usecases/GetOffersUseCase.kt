package com.jcdesign.headhunter.domain.usecases

import com.jcdesign.headhunter.domain.models.Offers
import com.jcdesign.headhunter.domain.repository.AppRepository

class GetOffersUseCase(
    private val repository: AppRepository
) {

    suspend fun execute(): Offers {
        return repository.getOffers()
    }
}