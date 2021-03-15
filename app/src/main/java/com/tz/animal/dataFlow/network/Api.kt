package com.tz.animal.dataFlow.network

import com.tz.animal.dataFlow.network.model.CatResponse
import com.tz.animal.dataFlow.network.model.DogResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("xim/api.php")
    fun getCats(@Query("query") category:String) : Single<CatResponse>

    @GET("xim/api.php")
    fun getDogs(@Query("query") category:String) : Single<DogResponse>
}