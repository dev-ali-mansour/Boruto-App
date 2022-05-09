package dev.alimansour.borutoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.alimansour.borutoapp.util.Constants.HERO_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = HERO_REMOTE_KEYS_DATABASE_TABLE)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
