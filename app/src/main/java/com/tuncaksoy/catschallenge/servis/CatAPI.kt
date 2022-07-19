package com.tuncaksoy.catschallenge.servis

import com.tuncaksoy.catschallenge.model.Cats
import io.reactivex.Single
import retrofit2.http.GET

interface CatAPI {
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getCats() : Single<List<Cats>>

}