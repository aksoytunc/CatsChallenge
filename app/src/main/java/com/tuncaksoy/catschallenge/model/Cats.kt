package com.tuncaksoy.catschallenge.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Cats(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val catGenus: String?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val catDefinition: String?,
    @ColumnInfo(name = "wikipedia_url")
    @SerializedName("wikipedia_url")
    val catWikipediaUrl: String?,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    var image : Items,
    /*@ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val catImageUrl: String?,*/
    @ColumnInfo(name = "favorites")
    @SerializedName("favorites")
    var catFavorites: Boolean?
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}


@Parcelize
data class Items( val url : String) : Parcelable {


}

//https://api.thecatapi.com/v1/breeds?limit=10&page=0?ae76962b-bfd9-406a-8d88-1a3965c41ad9
//https://api.thecatapi.com/v1/breeds/search?q=abc&api_key=824bd368-5bee-40a7-9deb-f0191db337f2 son bu