package com.econocom.gigirestaurants.di

import com.econocom.gigirestaurants.model.network.DataDownloader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataDownloaderModule {
    @Provides
    @Singleton
    fun provideDataDownloader(): DataDownloader { return DataDownloader() }
}