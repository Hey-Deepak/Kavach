package com.dc.kavach.di

import android.content.Context
import android.content.pm.PackageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePackageManager(
        @ApplicationContext appContext: Context
    ): PackageManager {
        return appContext.packageManager
    }

    @Singleton
    @Provides
    fun provideString(): String{
        return "Testing"
    }


}