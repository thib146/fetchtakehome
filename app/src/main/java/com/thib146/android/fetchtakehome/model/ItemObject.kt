package com.thib146.android.fetchtakehome.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemObject (
    @SerialName("id")
    val id: Long,
    @SerialName("listId")
    val listId: Int,
    @SerialName("name")
    val name: String? = null
)