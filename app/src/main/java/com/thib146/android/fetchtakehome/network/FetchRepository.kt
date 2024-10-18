package com.thib146.android.fetchtakehome.network

import android.util.Log
import com.thib146.android.fetchtakehome.database.FetchItemsDao
import com.thib146.android.fetchtakehome.database.asDatabaseModel
import com.thib146.android.fetchtakehome.database.asDomainModel
import com.thib146.android.fetchtakehome.model.ItemObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class FetchRepository @Inject constructor(
    private val fetchAPIService: FetchAPIService,
    private val database: FetchItemsDao
) {
    suspend fun getFetchItems(): List<ItemObject> {
        return withContext(Dispatchers.IO) {
            database.getAllFetchItems()?.asDomainModel() ?: listOf()
        }
    }

    suspend fun refreshFetchData(): Result<Boolean> {
        return try {
            withContext(Dispatchers.IO) {
                val fetchItems = fetchAPIService.getFetchData()
                if (fetchItems.isSuccessful) {
                    database.insertAll(*fetchItems.body()!!.asDatabaseModel())
                    Result.success(true)
                } else {
                    Result.success(false)
                    throw HttpException(fetchItems)
                }
            }
        } catch (e: Exception) {
            Log.e("Network Error: ", e.message.toString())
            Result.success(false)
        }
    }
}