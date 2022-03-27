package com.ronny.assignment.data.api

import com.ronny.assignment.models.TrendingRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET()
    suspend fun getRepo(@Url url:String, @Query("sort") sort:String,
                             @Query("order")order:String,
                             @Query("page")page:Int,
                             @Query("q")q:String,
                             @Query("per_page")per_page:String): Response<TrendingRepo>
}