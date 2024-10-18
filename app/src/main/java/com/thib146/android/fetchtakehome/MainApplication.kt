package com.thib146.android.fetchtakehome

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.Configuration
import com.thib146.android.fetchtakehome.model.AppVisibility
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltAndroidApp
class MainApplication: Application(), LifecycleEventObserver, Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        mainApplication = this
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {}
            Lifecycle.Event.ON_START -> {
                _appVisibilityState.value = AppVisibility.FOREGROUND
            }

            Lifecycle.Event.ON_RESUME -> {}
            Lifecycle.Event.ON_PAUSE -> {}
            Lifecycle.Event.ON_STOP -> {
                _appVisibilityState.value = AppVisibility.BACKGROUND
            }

            Lifecycle.Event.ON_DESTROY -> {}
            Lifecycle.Event.ON_ANY -> {}
        }
    }

    companion object {
        private lateinit var mainApplication: MainApplication

        private val _appVisibilityState = MutableStateFlow(AppVisibility.BACKGROUND)
        val appVisibilityState = _appVisibilityState.asStateFlow()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}