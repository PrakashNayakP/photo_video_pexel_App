package com.androdocs.vid_photo_app.api

import com.androdocs.vid_photo_app.models.photoresponse
import com.androdocs.vid_photo_app.models.videoresponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface getApi {
//    @Headers("Authorization: 563492ad6f9170000100000199e3f6569cde4fbb9554b208b8b77359")
//    @GET("/v1/curated?page=2&per_page=40")
//    fun getPexelsImage(): Call<photoresponse>


    @Headers("Authorization: 563492ad6f9170000100000199e3f6569cde4fbb9554b208b8b77359")
    @GET("videos/popular")
    fun getPexelsVideo(): Call<videoresponse>



    @Headers("Authorization: 563492ad6f9170000100000199e3f6569cde4fbb9554b208b8b77359")
    @GET("/v1/search")
    fun getSearchImage(@Query("query")query:String): Call<photoresponse>




}