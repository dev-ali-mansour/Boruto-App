package dev.alimansour.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.alimansour.borutoapp.data.local.converter.ListStringConverter
import dev.alimansour.borutoapp.data.local.dao.HeroDao
import dev.alimansour.borutoapp.data.local.dao.HeroRemoteKeyDao
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.data.local.entity.HeroRemoteKey

@TypeConverters(ListStringConverter::class)
@Database(entities = [HeroEntity::class, HeroRemoteKey::class], version = 1, exportSchema = false)
abstract class BorutoDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}