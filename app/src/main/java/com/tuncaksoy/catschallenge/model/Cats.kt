package com.tuncaksoy.catschallenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Cats(
    @ColumnInfo(name = "isim")
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
    var catFavorites : Boolean?
) {
@PrimaryKey(autoGenerate = true)
var uuid : Int = 0
}
