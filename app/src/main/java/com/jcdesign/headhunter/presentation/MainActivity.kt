package com.jcdesign.headhunter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jcdesign.headhunter.presentation.main_screen.MainScreen
import com.jcdesign.headhunter.ui.theme.Black
import com.jcdesign.headhunter.ui.theme.Green
import com.jcdesign.headhunter.ui.theme.HeadHunterTheme
import com.jcdesign.headhunter.utils.State
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HeadHunterTheme(darkTheme = true) {
                val viewModel = hiltViewModel<MainViewModel>()


                val state by viewModel.dataState.collectAsState()

                when (state) {
                    is State.Error -> ErrorScreen((state as State.Error).message)
                    is State.Loading -> LoadingScreen()
                    is State.Success -> {
                        val vacancies = (state as State.Success).vacancies
                        val offers = (state as State.Success).offers
                        MainScreen(offers, vacancies)
                    }
                }

            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        contentAlignment = Alignment.Center,

        ) {
        CircularProgressIndicator(
            color = Green
        )
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: $message", color = Color.Green)
    }
}




