package com.example.demospacex.db

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object DatabaseAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application, callback: FavoriteRocketDatabase.Callback) = Room.databaseBuilder(app, FavoriteRocketDatabase::class.java, "favorite_rocket")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideFavoriteRocketDao(db: FavoriteRocketDatabase) = db.favoriteRocketDao()

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}