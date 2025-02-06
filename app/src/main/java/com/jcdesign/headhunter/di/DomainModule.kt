package com.jcdesign.headhunter.di

import com.jcdesign.headhunter.domain.repository.AppRepository
import com.jcdesign.headhunter.domain.usecases.AddVacancyToFavoriteUseCase
import com.jcdesign.headhunter.domain.usecases.DeleteVacancyFromFavoriteUseCase
import com.jcdesign.headhunter.domain.usecases.GetFavoriteVacanciesUseCase
import com.jcdesign.headhunter.domain.usecases.GetOffersUseCase
import com.jcdesign.headhunter.domain.usecases.GetVacanciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetVacanciesUseCase(repository: AppRepository): GetVacanciesUseCase {
        return GetVacanciesUseCase(repository)
    }

    @Provides
    fun provideGetOffersUseCase(repository: AppRepository): GetOffersUseCase {
        return GetOffersUseCase(repository)
    }

    @Provides
    fun provideGetFavoriteVacanciesUseCase(repository: AppRepository): GetFavoriteVacanciesUseCase {
        return GetFavoriteVacanciesUseCase(repository)
    }

    @Provides
    fun provideAddVacancyToFavoriteUseCase(repository: AppRepository): AddVacancyToFavoriteUseCase {
        return AddVacancyToFavoriteUseCase(repository)
    }

    @Provides
    fun provideDeleteVacancyFromFavoriteUseCase(repository: AppRepository): DeleteVacancyFromFavoriteUseCase {
        return DeleteVacancyFromFavoriteUseCase(repository)
    }
}