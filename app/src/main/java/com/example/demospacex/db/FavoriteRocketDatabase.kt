package com.example.demospacex.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.demospacex.models.FavoriteRocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@TypeConverters(value = [MyTypeConverters::class])
@Database(entities = [FavoriteRocket::class], version = 1)
abstract class FavoriteRocketDatabase : RoomDatabase() {
    abstract fun favoriteRocketDao(): FavoriteRocketDao

    class Callback @Inject constructor(private val database: Provider<FavoriteRocketDatabase>) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            database.get().favoriteRocketDao()


        }

    }

}