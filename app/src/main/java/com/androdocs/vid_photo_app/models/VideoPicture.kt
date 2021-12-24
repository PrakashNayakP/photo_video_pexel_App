package com.androdocs.vid_photo_app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class VideoPicture(
    val id: Int,
    val nr: Int,
    val picture: String
):Parcelable