package com.androdocs.vid_photo_app.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fav:Favorite)


    @Delete
    suspend fun delete(fav:Favorite)

    @Query(value = "Select * from FavTable order by url ASC")
    fun getAllNote(): LiveData<List<Favorite>>
}