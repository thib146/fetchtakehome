package com.thib146.android.fetchtakehome.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.thib146.android.fetchtakehome.MainActivityViewModel
import com.thib146.android.fetchtakehome.ui.home.HomeScreen
import com.thib146.android.fetchtakehome.ui.navigation.Route
import com.thib146.android.fetchtakehome.ui.navigation.composableScreen

@Composable
fun AppUIContainer(
    viewModel: MainActivityViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val navController = rememberNavController()

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Route.HOME.value
        ) {
            composableScreen(
                route = Route.HOME.value
            ) {
                HomeScreen(viewModel = hiltViewModel(it))
            }
        }

    }
}