package com.makuno.memory.data.remote.api

import com.makuno.memory.data.models.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApplicationApi {

    @GET("admin/products.json")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("access_token") accessToken: String
    ): ProductsResponse

}