package dev.alimansour.borutoapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.alimansour.borutoapp.data.pref.DataStoreOperationsImpl
import dev.alimansour.borutoapp.domain.repository.DataStoreOperations
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations =
        DataStoreOperationsImpl(context = context)


}