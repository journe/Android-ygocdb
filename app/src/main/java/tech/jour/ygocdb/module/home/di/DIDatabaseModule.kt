package tech.jour.ygocdb.module.home.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tech.jour.ygocdb.room.AppDatabase
import tech.jour.ygocdb.room.dao.SearchHistoryDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DIDatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideSearchHistoryDao(appDatabase: AppDatabase): SearchHistoryDao {
        return appDatabase.searchHistoryDao()
    }

}
