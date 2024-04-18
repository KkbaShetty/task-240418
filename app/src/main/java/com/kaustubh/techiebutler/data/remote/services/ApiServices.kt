package com.kaustubh.techiebutler.data.remote.services

import com.kaustubh.techiebutler.model.TypeCodeItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("posts")
    fun getAllPosts(): Call<List<TypeCodeItem>>
}