package com.tuncaksoy.catschallenge.servis

import com.tuncaksoy.catschallenge.model.Cats
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatAPI {
    val deneme : String
    //@GET("?api_key=limit=10&page=0?ae76962b-bfd9-406a-8d88-1a3965c41ad9") //atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json // search?q=abc&api_key=824bd368-5bee-40a7-9deb-f0191db337f2
    @GET("search?q=a&?api_key=limit=10&page=0?ae76962b-bfd9-406a-8d88-1a3965c41ad9")
    fun getCats(): Single<List<Cats>>


    @GET("search")
    fun searchCat(
        @Query("x-api-key") id : String,
        @Query("q") searchCatView : String?
    ): Single<List<Cats>>

}