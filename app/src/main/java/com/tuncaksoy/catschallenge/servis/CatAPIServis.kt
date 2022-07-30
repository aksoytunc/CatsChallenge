package com.tuncaksoy.catschallenge.servis

import com.tuncaksoy.catschallenge.model.Cats
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CatAPIServis {
    private val BASE_URL = "https://api.thecatapi.com/v1/breeds/" //https://raw.githubusercontent.com/
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CatAPI::class.java)



    fun getData() : Single<List<Cats>> {
        return api.getCats()
    }

    fun getSearchCat() : Single<List<Cats>>{
        return api.searchCat("a","0?ae76962b-bfd9-406a-8d88-1a3965c41ad9")
    }
}

