package com.dc.avarodh.di

import android.content.Context
import android.content.pm.PackageManager
import com.google.firebase.firestore.core.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ServiceScoped
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