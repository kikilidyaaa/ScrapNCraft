package com.bangkit.scrapncraft.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkit.scrapncraft.data.remote.response.DataItem

@Database(entities = [DataItem::class], version = 1, exportSchema = false)
abstract class CraftsDatabase : RoomDatabase() {
    abstract fun craftsDao(): CraftsDao

    companion object {
        @Volatile
        private var instance: CraftsDatabase? = null
        fun getInstance(context: Context): CraftsDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    CraftsDatabase::class.java, "Crafts.db"
                ).build()
            }
    }
}