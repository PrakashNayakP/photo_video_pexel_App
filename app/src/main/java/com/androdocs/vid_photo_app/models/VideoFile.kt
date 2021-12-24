package com.androdocs.vid_photo_app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class VideoFile(
    val file_type: String,
    val height: Int,
    val id: Int,
    val link: String,
    val quality: String,
    val width: Int
):Parcelable