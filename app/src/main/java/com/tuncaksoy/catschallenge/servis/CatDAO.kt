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

    @Query("SELECT * FROM Cats WHERE id")
    fun getAllGenus() : List<Cats>

    @Query("SELECT * FROM cats WHERE uuid = :uuid")
    fun getCat(uuid : Int) : Cats

    @Query("SELECT * FROM cats WHERE id = :catGenus")
    fun getCatGenus(catGenus: String) : Cats

    @Query("DELETE FROM cats")
    fun deleteAllCat()

    @Query("DELETE FROM cats Where id = :catGenus")
    fun deleteCat(catGenus : String)
}