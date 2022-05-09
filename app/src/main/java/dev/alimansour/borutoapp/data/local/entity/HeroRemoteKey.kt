package dev.alimansour.borutoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.alimansour.borutoapp.util.Constants.HERO_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = HERO_REMOTE_KEY_DATABASE_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
