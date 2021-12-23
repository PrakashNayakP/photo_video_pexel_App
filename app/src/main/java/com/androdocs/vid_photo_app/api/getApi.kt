package com.androdocs.vid_photo_app.api

import com.androdocs.vid_photo_app.models.photoresponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface getApi {
    @Headers("Authorization: 563492ad6f9170000100000199e3f6569cde4fbb9554b208b8b77359")
    @GET("/v1/curated")
    fun getPexelsImage(): Call<photoresponse>
}