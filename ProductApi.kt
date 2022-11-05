package com.example.productlistapplicatoin.Model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://mobileinterview.azurewebsites.net"

interface ProductApi {
    @GET("/api/products")
    fun getProductData(
        @Query("PageIndex") page: Int,
        @Query("PageSize") limit: Int
    ): Call<ProductListModel>
}

object ProductServices {
    var productInstance: ProductApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productInstance = retrofit.create(ProductApi::class.java)
    }
}