package com.thib146.android.fetchtakehome.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thib146.android.fetchtakehome.R
import com.thib146.android.fetchtakehome.model.ItemObject
import com.thib146.android.fetchtakehome.ui.theme.FetchTheme

@Composable
fun FetchItem(
    itemData: ItemObject
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(
                    255 * itemData.listId,
                    255 / itemData.listId,
                    255 / itemData.listId,
                    255 * itemData.listId
                )
            )
            .clickable { }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.fetch_id_text, itemData.id)
                )
                Text(
                    text = stringResource(R.string.fetch_item_listid_text, itemData.listId)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                text = stringResource(R.string.fetch_item_name_text, itemData.name ?: "")
            )
            Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp))
        }
    }
}

@Preview
@Composable
fun FetchItemPreview() {
    FetchTheme {
        FetchItem(
            ItemObject(
                id = 1,
                listId = 1,
                name = "Item 1"
            )
        )
    }
}