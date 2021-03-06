package dev.alimansour.borutoapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.alimansour.borutoapp.data.local.DataStoreOperationsImpl
import dev.alimansour.borutoapp.data.repository.RepositoryImpl
import dev.alimansour.borutoapp.domain.local.DataStoreOperations
import dev.alimansour.borutoapp.domain.local.LocalDataSource
import dev.alimansour.borutoapp.domain.remote.RemoteDataSource
import dev.alimansour.borutoapp.domain.repository.Repository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations =
        DataStoreOperationsImpl(context = context)

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        datastore: DataStoreOperations
    ): Repository =
        RepositoryImpl(
            local = localDataSource,
            remote = remoteDataSource,
            datastore = datastore
        )
}