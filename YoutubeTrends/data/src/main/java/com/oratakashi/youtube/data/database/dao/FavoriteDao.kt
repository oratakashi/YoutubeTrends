package com.oratakashi.youtube.data.database.dao

import androidx.room.*
import com.oratakashi.youtube.data.model.fav.Favorite
import io.reactivex.Single

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data: Favorite): Single<Long>

    @Delete
    fun delete(data: Favorite): Single<Int>

    @Query("Select * from favorite where id=:id")
    fun getById(id: String): Single<List<Favorite>>

    @Query("Select * from favorite")
    fun getAll(): Single<List<Favorite>>

    @Query("Select * from favorite where categoryId=:id")
    fun getByCategory(id: String): Single<List<Favorite>>
}