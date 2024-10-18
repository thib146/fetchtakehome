package com.thib146.android.fetchtakehome.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thib146.android.fetchtakehome.model.ItemObject

@Entity
data class DatabaseFetchItem (
    @PrimaryKey
    val id: Long,
    val listId: Int,
    val name: String?,
)

fun List<DatabaseFetchItem>.asDomainModel(): List<ItemObject> {
    return map {
        ItemObject(
            id = it.id,
            listId = it.listId,
            name = it.name
        )
    }
}