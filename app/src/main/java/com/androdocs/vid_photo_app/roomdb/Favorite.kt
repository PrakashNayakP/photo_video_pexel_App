package com.androdocs.vid_photo_app.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "FavTable")
class Favorite(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    var id: Int,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="url")val url:String,
    @ColumnInfo(name="name")val name:String,
    @ColumnInfo(name="type")val type:Boolean,
    @ColumnInfo(name="image")val image:String,
    @ColumnInfo(name="desc")val desc:String
)
//{
//        @PrimaryKey(autoGenerate = true)
//        var id=0
//
//}