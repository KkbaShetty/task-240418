package com.kaustubh.techiebutler.data.remote.services

import com.kaustubh.techiebutler.model.TypeCodeItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("posts")
    fun getAllPosts(
        @Query("_start") startAt: Int,
        @Query("_limit") pageSize: Int,
    ): Call<List<TypeCodeItem>>
}