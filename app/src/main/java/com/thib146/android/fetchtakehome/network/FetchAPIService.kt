package com.thib146.android.fetchtakehome.network

import com.thib146.android.fetchtakehome.model.ItemObject
import retrofit2.Response
import retrofit2.http.GET

interface FetchAPIService {
    @GET("/hiring.json")
    suspend fun getFetchData(): Response<List<ItemObject>>
}