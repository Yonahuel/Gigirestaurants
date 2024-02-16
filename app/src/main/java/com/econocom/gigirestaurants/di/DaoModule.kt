package com.econocom.gigirestaurants.di

import com.econocom.gigirestaurants.database.AppDatabase
import com.econocom.gigirestaurants.database.daos.RestaurantDao
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
    fun provideDao(appDatabase: AppDatabase): RestaurantDao { return appDatabase.favoritosDao() }
}