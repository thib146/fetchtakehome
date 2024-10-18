package com.thib146.android.fetchtakehome.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thib146.android.fetchtakehome.R

@Composable
fun TitleItem(
    listId: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Gray)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.list_id, listId)
        )
    }
}