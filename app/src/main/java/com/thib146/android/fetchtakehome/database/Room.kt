package com.thib146.android.fetchtakehome.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface FetchItemsDao {

    @Query("SELECT * FROM databasefetchitem ORDER BY listId, name+0 ASC")
    fun getAllFetchItems(): List<DatabaseFetchItem>?

    @Query("SELECT * FROM databasefetchitem WHERE listId = :listId ORDER BY name ASC")
    fun getFetchItemsByListId(listId: Int): List<DatabaseFetchItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg fetchItems: DatabaseFetchItem)

    @Query("DELETE FROM databasefetchitem")
    fun deleteAllFetchItems()
}

@Database(entities = [DatabaseFetchItem::class], version = 1)
abstract class FetchItemsDatabase: RoomDatabase() {
    abstract val fetchItemsDao: FetchItemsDao
}