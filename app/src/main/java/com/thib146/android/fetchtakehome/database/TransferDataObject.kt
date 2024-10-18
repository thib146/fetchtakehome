package com.thib146.android.fetchtakehome.database

import com.thib146.android.fetchtakehome.model.ItemObject

fun List<ItemObject>.asDatabaseModel(): Array<DatabaseFetchItem> {
    return map {
        DatabaseFetchItem (
            id = it.id,
            listId = it.listId,
            name = it.name
        )
    }.toTypedArray()
}