package com.thib146.android.fetchtakehome.di

import android.content.Context
import androidx.room.Room
import com.thib146.android.fetchtakehome.network.FetchAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.thib146.android.fetchtakehome.database.FetchItemsDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com"

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().apply {
                addInterceptor(getBasicLoggingInterceptor())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
            }.build()
        }

        @Singleton
        @Provides
        fun provideFetchService(
            okHttpClient: OkHttpClient
        ): FetchAPIService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(getConverterFactory())
            .client(okHttpClient.newBuilder().build())
            .build().create(FetchAPIService::class.java)

        private fun getBasicLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        }

        private fun getConverterFactory(): Converter.Factory {
            val json = Json {
                ignoreUnknownKeys = true
            }
            return json.asConverterFactory("application/json".toMediaType())
        }

        @Singleton
        @Provides
        fun provideFetchItemsDatabase(
            @ApplicationContext application: Context
        ) = Room.databaseBuilder(
            context = application,
            klass = FetchItemsDatabase::class.java,
            name = "fetchitems"
        ).build()

        @Singleton
        @Provides
        fun provideFetchItemsDao(db: FetchItemsDatabase) = db.fetchItemsDao
    }
}