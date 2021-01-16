package com.oratakashi.youtube.data.database.dao

import androidx.room.*
import com.oratakashi.youtube.data.model.fav.Favorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data: Favorite)

    @Delete
    fun delete(data: Favorite)

    @Query("Select * from favorite where id=:id")
    fun getById(id: String) : List<Favorite>
}