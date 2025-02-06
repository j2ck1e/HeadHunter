package com.jcdesign.headhunter.navigation.bottom_items

import com.jcdesign.headhunter.R
import com.jcdesign.headhunter.navigation.screens.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: Int
) {

    object Find: NavigationItem(
        screen = Screen.Find,
        titleResId = R.string.find_string,
        icon = R.drawable.find_default
    )

    object Favorite: NavigationItem(
        screen = Screen.Favorite,
        titleResId = R.string.favorite_string,
        icon = R.drawable.default_heart
    )

    object Letter: NavigationItem(
        screen = Screen.Letter,
        titleResId = R.string.letter_string,
        icon = R.drawable.letter_default
    )

    object Message: NavigationItem(
        screen = Screen.Message,
        titleResId = R.string.message_string,
        icon = R.drawable.message_default
    )

    object Profile: NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.profile_string,
        icon = R.drawable.profile_default
    )
}