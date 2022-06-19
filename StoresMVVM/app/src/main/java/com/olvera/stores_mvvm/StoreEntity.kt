package com.olvera.stores_mvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StoreEntity")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var phone: String = "",
    var webSite: String = "",
    var isFavorite: Boolean = false)
