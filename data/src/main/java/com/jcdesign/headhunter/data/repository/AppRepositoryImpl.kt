package com.jcdesign.headhunter.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.jcdesign.headhunter.data.mapper.Mapper
import com.jcdesign.headhunter.data.network.ApiService
import com.jcdesign.headhunter.data.room.VacancyDao
import com.jcdesign.headhunter.domain.models.Offers
import com.jcdesign.headhunter.domain.models.Vacancies
import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService ,
    private val dao: VacancyDao,
    private val mapper: Mapper
) : AppRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getVacancies(): Vacancies {
        val responseDto = apiService.loadData()
        return mapper.mapResponseDtoToVacancies(responseDto)
    }

    override suspend fun getOffers(): Offers {
        val responseDto = apiService.loadData()
        return mapper.mapResponseDtoToOffers(responseDto)
    }

    override suspend fun getVacancyById(id: String): Vacancy {
        TODO()
    }

    override suspend fun searchVacancyByKeyWord(keyWord: String): Vacancies {
        TODO()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getFavoriteVacancies(): Flow<List<Vacancy>> {
        return dao.getAllFavoriteVacancies()
            .map { entityList -> mapper.mapEntityListToVacancy(entityList) }
    }

    override suspend fun addVacancyToFavorite(vacancy: Vacancy): Boolean {
        val entity = mapper.mapVacancyToEntity(vacancy)
        dao.addToFavorites(entity)
        return true
    }

    override suspend fun removeVacancyFromFavorite(vacancy: Vacancy): Boolean {
        dao.removeFromFavorites(vacancy.id)
        return true
    }

}