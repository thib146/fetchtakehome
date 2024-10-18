package com.thib146.android.fetchtakehome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thib146.android.fetchtakehome.model.ItemObject
import com.thib146.android.fetchtakehome.network.FetchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fetchRepository: FetchRepository
): ViewModel() {

    private var _fetchItems = MutableLiveData<List<ItemObject>>()
    val fetchItems: LiveData<List<ItemObject>>
        get() = _fetchItems

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        refreshData()
    }

    fun refreshData() {
        _isLoading.value = true
        viewModelScope.launch {
            when(fetchRepository.refreshFetchData()) {
                Result.success(true) -> {
                    _isLoading.value = false
                }
                Result.success(false) -> {
                    _isLoading.value = false
                }
            }
            _fetchItems.value = fetchRepository.getFetchItems()
        }
    }
}