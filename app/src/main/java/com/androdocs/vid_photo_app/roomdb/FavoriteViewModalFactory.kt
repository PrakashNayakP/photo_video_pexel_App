package com.androdocs.vid_photo_app.roomdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androdocs.vid_photo_app.roomdb.FavoriteRepository

class FavoriteViewModalFactory(private val repository:FavoriteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModal(repository) as T
    }
}
