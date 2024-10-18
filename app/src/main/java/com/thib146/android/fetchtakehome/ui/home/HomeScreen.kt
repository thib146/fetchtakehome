package com.thib146.android.fetchtakehome.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thib146.android.fetchtakehome.MainActivityViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    val listData = viewModel.fetchItems.asFlow().collectAsStateWithLifecycle(
        initialValue = null,
        minActiveState =  Lifecycle.State.CREATED
    )
    val listState = rememberLazyListState()
    val isLoading = viewModel.isLoading.asFlow().collectAsState(true)

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isLoading.value,
        onRefresh = {
            viewModel.refreshData()
        })

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .pullRefresh(pullRefreshState),
            state = listState
        ) {
            val maxListId = listData.value?.maxByOrNull { it.listId }?.listId
            maxListId?.let {
                for (i in 1..maxListId) {
                    item {
                        TitleItem(i)
                    }
                    val listDataByListId = listData.value?.filter { it.listId == i }
                    items(
                        count = listDataByListId?.size ?: 0
                    ) { index ->
                        val itemData = listDataByListId?.get(index)
                        itemData?.let {
                            if (!itemData.name.isNullOrEmpty()) {
                                FetchItem(itemData)
                            }
                        }
                    }
                }
            }
        }
        PullRefreshIndicator(
            isLoading.value,
            pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}