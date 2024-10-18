package com.thib146.android.fetchtakehome

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.thib146.android.fetchtakehome.ui.AppUIContainer
import com.thib146.android.fetchtakehome.ui.theme.FetchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainActivityViewModel = hiltViewModel<MainActivityViewModel>()

            FetchTheme {
                AppUIContainer(mainActivityViewModel)
            }
        }
    }
}