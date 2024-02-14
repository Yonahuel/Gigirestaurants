package com.econocom.gigirestaurants.di

import com.econocom.gigirestaurants.database.AppDatabase
import com.econocom.gigirestaurants.database.daos.FavoritosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): FavoritosDao { return appDatabase.favoritosDao() }
}