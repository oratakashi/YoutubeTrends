package com.oratakashi.youtube.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oratakashi.youtube.data.model.fav.*

@Database(
    entities = [
        Items::class,
        Medium::class,
        Snippet::class,
        Standard::class,
        Statistics::class
    ],
    version = 1
)
abstract class RoomDB : RoomDatabase() {
    companion object {

        @Volatile
        private var instance: RoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "YoutubeTrendsFav.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}