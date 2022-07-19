package com.tuncaksoy.catschallenge.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import com.tuncaksoy.catschallenge.model.Cats

class CatSharedPreferences {
    companion object {
        val cats = MutableLiveData<List<Cats>>()
        private val TIME = "time"
        private var sharedPreferences: SharedPreferences? = null
        @Volatile
        private var instance: CatSharedPreferences? = null
        private val lock = Any()
        operator fun invoke(context: Context): CatSharedPreferences =
            instance ?: synchronized(lock) {
                instance ?: creatCatSharedPrefences(context).also {
                    instance = it
                }
            }

        private fun creatCatSharedPrefences(context: Context): CatSharedPreferences {
            sharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return CatSharedPreferences()
        }
    }

    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true) {
            putLong(TIME,time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(TIME,0)
}