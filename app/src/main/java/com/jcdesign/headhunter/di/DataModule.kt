package com.jcdesign.headhunter.di

import android.content.Context
import androidx.room.Room
import com.jcdesign.headhunter.data.mapper.Mapper
import com.jcdesign.headhunter.data.network.ApiFactory
import com.jcdesign.headhunter.data.network.ApiService
import com.jcdesign.headhunter.data.repository.AppRepositoryImpl
import com.jcdesign.headhunter.data.room.VacancyDao
import com.jcdesign.headhunter.data.room.db.AppDatabase
import com.jcdesign.headhunter.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideVacancyDao(db: AppDatabase): VacancyDao {
        return db.dao()
    }

    @Provides
    @Singleton
    fun provideMapper(): Mapper {
        return Mapper()
    }

    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        vacancyDao: VacancyDao,
        mapper: Mapper
    ): AppRepository {
        return AppRepositoryImpl(
            apiService,
            vacancyDao,
            mapper)
    }
}