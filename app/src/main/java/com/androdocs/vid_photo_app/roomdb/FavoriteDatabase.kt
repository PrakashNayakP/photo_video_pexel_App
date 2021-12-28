package com.androdocs.vid_photo_app.roomdb

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Favorite::class),version = 1,exportSchema = false)
abstract class FavoriteDatabase:RoomDatabase() {


    abstract val getFavoritesDao:FavDao

    companion object{

        @Volatile
        private var INSTANCE:FavoriteDatabase? = null

//        fun getDatabase(context: Context):FavoriteDatabase{
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    FavoriteDatabase::class.java,
//                    "favorite_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//
//        }

        fun getInstance(context: Context):FavoriteDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorite_database"
                    ).build()
                }
                Log.d("Success", "returning database instance")
                return instance
            }
        }

    }
}