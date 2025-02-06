package com.jcdesign.headhunter.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(

) {
    @Serializable
    object Find : Screen()

    @Serializable
    data class DetailVacancy(
        val description: String = ""
    ) : Screen()

    @Serializable
    object Favorite : Screen()

    @Serializable
    object Letter : Screen()

    @Serializable
    object Message : Screen()

    @Serializable
    object Profile : Screen()
}
