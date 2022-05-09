package dev.alimansour.borutoapp.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.alimansour.borutoapp.data.local.BorutoDatabase
import dev.alimansour.borutoapp.data.remote.BorutoApi
import dev.alimansour.borutoapp.data.remote.RemoteDataSourceImpl
import dev.alimansour.borutoapp.domain.remote.RemoteDataSource
import dev.alimansour.borutoapp.util.Constants.BASE_URL
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

    @Singleton
    @Provides
    fun provideBorutoApi(retrofit: Retrofit): BorutoApi =
        retrofit.create(BorutoApi::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        borutoApi: BorutoApi,
        borutoDatabase: BorutoDatabase
    ): RemoteDataSource =
        RemoteDataSourceImpl(borutoApi = borutoApi, borutoDatabase = borutoDatabase)
}