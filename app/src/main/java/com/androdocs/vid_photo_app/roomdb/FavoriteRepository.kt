package com.androdocs.vid_photo_app.roomdb

import android.util.Log
import androidx.lifecycle.LiveData

class FavoriteRepository(private val favDao: FavDao){
    val allNotes: LiveData<List<Favorite>> = favDao.getAllNote()

    suspend fun  insert(favorite:Favorite){
        favDao.insert(favorite)
    }

    suspend fun delete(favorite: Favorite){
        favDao.delete(favorite)
    }

    suspend fun isRecordExists(url: String?)=favDao.isRecordExists(url)

}