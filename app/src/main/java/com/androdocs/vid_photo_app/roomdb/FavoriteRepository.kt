package com.androdocs.vid_photo_app.roomdb

import android.util.Log
import androidx.lifecycle.LiveData

class FavoriteRepository(private val favDao: FavDao){
    val allNotes: LiveData<List<Favorite>> = favDao.getAllNote()

    suspend fun  insert(favorite:Favorite){
        Log.d("Success", "in repository")
        favDao.insert(favorite)
    }

    suspend fun delete(favorite: Favorite){
        favDao.delete(favorite)
    }
}