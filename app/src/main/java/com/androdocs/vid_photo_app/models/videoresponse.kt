package com.androdocs.vid_photo_app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class videoresponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val total_results: Int,
    val url: String,
    val videos: List<Video>
):Parcelable