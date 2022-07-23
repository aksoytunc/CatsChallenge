package com.tuncaksoy.catschallenge.servis

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tuncaksoy.catschallenge.model.Cats
@Dao
interface CatDAO {
    @Insert
    //fun insertCat(cats: Cats)
    fun insertAll(vararg cats: Cats) : List<Long>

    @Insert
    fun insert(cats: Cats)

    @Query("SELECT * FROM Cats")
    fun getAllCat() : List<Cats>

    @Query("SELECT * FROM cats WHERE uuid = :catId")
    fun getCat(catId : Int) : Cats

    @Query("DELETE FROM cats")
    fun deleteAllCat()

    @Query("DELETE FROM cats Where isim = :catGenus")
    fun deleteCat(catGenus : String)
}