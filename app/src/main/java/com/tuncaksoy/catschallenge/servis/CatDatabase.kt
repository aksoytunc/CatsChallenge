package com.tuncaksoy.catschallenge.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tuncaksoy.catschallenge.model.Cats
import com.tuncaksoy.catschallenge.model.TypeConverter

@Database(entities = arrayOf(Cats::class), version = 1)
@TypeConverters(TypeConverter::class)

abstract class CatDatabase : RoomDatabase() {

    abstract fun catDao(): CatDAO

    companion object {
        @Volatile private var instance: CatDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: creatDatabase(context).also {
                instance = it
            }
        }

        private fun creatDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CatDatabase::class.java, "catdatabase"
        ).build()
    }
}