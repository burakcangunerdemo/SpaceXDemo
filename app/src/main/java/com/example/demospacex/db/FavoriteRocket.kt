package com.example.demospacex.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "favorite_rocket")
data class FavoriteRocket(

    @PrimaryKey
    @ColumnInfo(name = "rocket_id")
    var rocketId: String,

    @ColumnInfo(name = "rocket")
    var rocket: RocketsItem

)
