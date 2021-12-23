package com.androdocs.vid_photo_app.models

data class photoresponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)