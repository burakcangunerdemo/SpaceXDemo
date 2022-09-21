package com.example.demospacex.db

import androidx.room.*
import com.example.demospacex.models.FavoriteRocket
import com.example.demospacex.models.RocketsItem


@Dao
interface FavoriteRocketDao {

    @Query("SELECT rocket FROM favorite_rocket")
    suspend fun getAllFavoriteRockets(): List<RocketsItem>

    @Query("SELECT COUNT(*) FROM favorite_rocket WHERE rocket_id = :rocketId")
    suspend fun getOneFavoriteRocket(rocketId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRocket(favoriteRocket: FavoriteRocket) : Long

    @Delete
    suspend fun deleteFavoriteRocket(favoriteRocket: FavoriteRocket) : Int

}