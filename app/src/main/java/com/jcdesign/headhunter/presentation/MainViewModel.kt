package com.jcdesign.headhunter.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcdesign.headhunter.domain.models.Vacancy
import com.jcdesign.headhunter.domain.usecases.AddVacancyToFavoriteUseCase
import com.jcdesign.headhunter.domain.usecases.DeleteVacancyFromFavoriteUseCase
import com.jcdesign.headhunter.domain.usecases.GetFavoriteVacanciesUseCase
import com.jcdesign.headhunter.domain.usecases.GetOffersUseCase
import com.jcdesign.headhunter.domain.usecases.GetVacanciesUseCase
import com.jcdesign.headhunter.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getOffersUseCase: GetOffersUseCase,
    ///// Favorite //////
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase,
    private val addVacancyToFavoriteUseCase: AddVacancyToFavoriteUseCase,
    private val removeVacancyFromFavoriteUseCase: DeleteVacancyFromFavoriteUseCase
) : ViewModel() {

    private val _dataState = MutableStateFlow<State>(State.Loading)
    val dataState: StateFlow<State>
        get() = _dataState

    init {
        loadFavorites()
        loadData()
    }

    private fun loadData() {
        _dataState.value = State.Loading

        viewModelScope.launch {
            try {
                val vacancies = getVacanciesUseCase.execute()
                val updatedVacancies = vacancies.vacancies.map { vacancy ->
                    vacancy.copy(isFavorite = _favoriteVacancies.value.any {
                        it.id == vacancy.id
                    })
                }

                val offers = getOffersUseCase.execute()

                _dataState.value = State.Success(updatedVacancies, offers.offers)

            } catch (e: Exception) {
                _dataState.value = State.Error(e.message ?: "Unknown error")
            }
        }
    }

    ///// *******************************Favorite******************************* /////

    private val _favoriteVacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val favoriteVacancies: StateFlow<List<Vacancy>> = _favoriteVacancies


    fun loadFavorites() {
        viewModelScope.launch {
            getFavoriteVacanciesUseCase.execute().collect { favorites ->
                _favoriteVacancies.value = favorites
                updateVacanciesWithFavorites()
            }
        }
    }

    fun addVacancyToFavorite(vacancy: Vacancy) {
        viewModelScope.launch {
            addVacancyToFavoriteUseCase.execute(vacancy)
            updateVacanciesWithFavorites()
        }
    }

    fun removeVacancyFromFavorite(vacancy: Vacancy) {
        viewModelScope.launch {
            removeVacancyFromFavoriteUseCase.execute(vacancy)
            updateVacanciesWithFavorites()
        }
    }

    private fun updateVacanciesWithFavorites() {
        val currentState = _dataState.value
        if (currentState is State.Success) {
            val updatedVacancies = currentState.vacancies.map { vacancy ->
                vacancy.copy(isFavorite = _favoriteVacancies.value.any { it.id == vacancy.id })
            }
            _dataState.value = currentState.copy(vacancies = updatedVacancies)
        }
    }

    ///// *******************************Offer******************************* /////

    fun onOfferClick(link: String, context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Не удалось открыть ссылку", Toast.LENGTH_SHORT).show()
        }
    }

}