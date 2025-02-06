package com.jcdesign.headhunter.domain.models

data class Vacancies(
    val vacancies: List<Vacancy>
)

data class Vacancy(
    val house: String = "",
    val street: String = "",
    val town: String = "",
    val appliedNumber: Int = 0,
    val company: String = "",
    val description: String = "",
    val previewExperienceText: String = "",
    val experienceText: String = "",
    val id: String = "",
    val isFavorite: Boolean = false,
    val lookingNumber: String = "",
    val publishedDate: String = "",
    val questions: List<String> = listOf(),
    val responsibilities: String = "",
    val salary: String = "",
    val schedules: List<String> = listOf(),
    val title: String = ""
)

