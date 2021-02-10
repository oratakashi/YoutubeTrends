package com.oratakashi.youtube.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oratakashi.youtube.data.database.dao.FavoriteDao
import com.oratakashi.youtube.data.model.fav.Favorite
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [
        Favorite::class
    ],
    version = 1
)
abstract class RoomDB : RoomDatabase() {

    abstract fun fav(): FavoriteDao

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
            .openHelperFactory(provideFactory())
            .build()

        private fun providePassPhrase() : ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        private fun provideFactory() : SupportFactory = SupportFactory(providePassPhrase())
    }
}