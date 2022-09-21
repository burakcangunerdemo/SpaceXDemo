package com.example.demospacex.db

import com.example.demospacex.models.FavoriteRocket
import javax.inject.Inject

class FavoriteRocketRepository
@Inject constructor(private val favoriteRocketDao : FavoriteRocketDao) {

    suspend fun getAllFavoriteRockets() = favoriteRocketDao.getAllFavoriteRockets()

    suspend fun insertFavoriteRocket(favoriteRocket: FavoriteRocket) = favoriteRocketDao.insertFavoriteRocket(favoriteRocket)

    suspend fun deleteFavoriteRocket(favoriteRocket: FavoriteRocket) = favoriteRocketDao.deleteFavoriteRocket(favoriteRocket)

    suspend fun getOneFavoriteRocket(rocketId: String) = favoriteRocketDao.getOneFavoriteRocket(rocketId)
}

