package dev.alimansour.borutoapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.alimansour.borutoapp.data.local.BorutoDatabase
import dev.alimansour.borutoapp.data.local.LocalDataSourceImpl
import dev.alimansour.borutoapp.domain.local.LocalDataSource
import dev.alimansour.borutoapp.util.Constants.BORUTO_DATABASE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BorutoDatabase =
        Room.databaseBuilder(
            context,
            BorutoDatabase::class.java,
            BORUTO_DATABASE
        ).build()

    @Singleton
    @Provides
    fun provideLocalDataSource(borutoDatabase: BorutoDatabase): LocalDataSource =
        LocalDataSourceImpl(borutoDatabase = borutoDatabase)
}