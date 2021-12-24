package com.androdocs.vid_photo_app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue





@Parcelize
data class Video(
//    val avg_color:@RawValue Any,
    val duration: Int,
//    val full_res:@RawValue Any,
    val height: Int,
    val id: Int,
    val image: String,
//    val tags: @RawValue List<Any>,
    val url: String,
    val user: User,
    val video_files:@RawValue List<VideoFile>,
    val video_pictures:@RawValue  List<VideoPicture>,
    val width: Int
):Parcelable