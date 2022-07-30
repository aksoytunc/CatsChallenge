package com.tuncaksoy.catschallenge.servis

import com.tuncaksoy.catschallenge.model.Cats
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CatAPI {
    @GET("?api_key=limit=10&page=0?ae76962b-bfd9-406a-8d88-1a3965c41ad9") //atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    fun getCats(): Single<List<Cats>>

    @GET("search?q=")
    fun searchCat(
        @Query("q") searchCatView : String,
        @Query("api_key") id : String
    ): Single<List<Cats>>

}