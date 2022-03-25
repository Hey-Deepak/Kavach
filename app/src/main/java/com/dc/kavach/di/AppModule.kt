package com.dc.kavach.di

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import com.dc.kavach.data.repository.firebase.BannedAppsRepositoryImpl
import com.dc.kavach.data.repository.firebase.UserBannedAppCountRepositoryImpl
import com.dc.kavach.data.repository.local.LocalAppRepositoryImpl
import com.dc.kavach.data.repository.local.UserRepositoryImpl
import com.dc.kavach.domain.repository.BannedAppsRepository
import com.dc.kavach.domain.repository.LocalAppRepository
import com.dc.kavach.domain.repository.UserBannedAppCountRepository
import com.dc.kavach.domain.repository.UserRepository
import com.dc.kavach.domain.usecase.BannedAppsUsecase
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
    fun provideUserRepository(
        @ApplicationContext appContext: Context
    ): UserRepository {
        return UserRepositoryImpl(
            appContext.getSharedPreferences("main", ComponentActivity.MODE_PRIVATE)
        )
    }

    @Singleton
    @Provides
    fun provideBannedAppsRepository(): BannedAppsRepository {
        return BannedAppsRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideLocalAppRepository(
        packageManager: PackageManager
    ): LocalAppRepository {
        return LocalAppRepositoryImpl(
            packageManager
        )
    }

    @Singleton
    @Provides
    fun provideUserBannedAppCountRepository(): UserBannedAppCountRepository {
        return UserBannedAppCountRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideBannedAppsUsecase(
        bannedAppsRepository: BannedAppsRepository,
        localAppRepository: LocalAppRepository
    ): BannedAppsUsecase {
        return BannedAppsUsecase(
            bannedAppsRepository, localAppRepository
        )
    }




}