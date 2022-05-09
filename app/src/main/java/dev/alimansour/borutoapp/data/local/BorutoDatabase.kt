package dev.alimansour.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.alimansour.borutoapp.data.local.converter.ListStringConverter
import dev.alimansour.borutoapp.data.local.dao.HeroDao
import dev.alimansour.borutoapp.data.local.dao.HeroRemoteKeysDao
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.data.local.entity.HeroRemoteKeys

@TypeConverters(ListStringConverter::class)
@Database(entities = [HeroEntity::class, HeroRemoteKeys::class], version = 1, exportSchema = false)
abstract class BorutoDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}