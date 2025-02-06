package com.jcdesign.headhunter.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jcdesign.headhunter.data.room.models.VacancyEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface VacancyDao {

    @Query("SELECT * FROM favorite_vacancies")
    fun getAllFavoriteVacancies(): Flow<List<VacancyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(vacancy: VacancyEntity)

    @Query("DELETE FROM favorite_vacancies WHERE id= :vacancyId")
    suspend fun removeFromFavorites(vacancyId: String)
}