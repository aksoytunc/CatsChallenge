package com.tuncaksoy.catschallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Cats(
    @ColumnInfo(name = "Id")
    @SerializedName("isim")
    val catGenus: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val catDefinition: String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val catWikipediaUrl: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val catImageUrl: String?,
    @ColumnInfo(name = "favorites")
    @SerializedName("favorites")
    var catFavorites: Boolean?,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    var catImage : Image
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

data class Image(
    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url : String?
) {
}

//https://api.thecatapi.com/v1/breeds?limit=10&page=0?ae76962b-bfd9-406a-8d88-1a3965c41ad9
//asdasd
//https://api.thecatapi.com/v1/breeds/search?q=abc&api_key=824bd368-5bee-40a7-9deb-f0191db337f2 son bu