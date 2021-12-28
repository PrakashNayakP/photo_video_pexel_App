package com.androdocs.vid_photo_app.roomdb

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModal(private val repository: FavoriteRepository): ViewModel() {

       var allfavorites = repository.allNotes

    fun deleteFavorite (favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(favorite)
        allfavorites = repository.allNotes
    }

    fun addFavorite(favorite: Favorite) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("Success", "in view model")
        repository.insert(favorite)
    }


}