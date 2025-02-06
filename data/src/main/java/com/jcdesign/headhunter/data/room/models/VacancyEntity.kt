package com.jcdesign.headhunter.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_vacancies")
data class VacancyEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val salary: String,
    val town: String,
    val company: String,
    val previewExperienceText: String,
    val publishedDate: String,
    val isFavorite: Boolean
)
