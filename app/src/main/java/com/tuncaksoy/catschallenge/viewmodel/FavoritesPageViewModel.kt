package com.tuncaksoy.catschallenge.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tuncaksoy.catschallenge.model.Cats
import com.tuncaksoy.catschallenge.servis.CatDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class FavoritesPageViewModel(application: Application) : BaseViewModel(application) {
    val cats = MutableLiveData<List<Cats>>()

    fun refreshData() {
        getDataSQLite()
    }

    private fun getDataSQLite() {
        viewModelScope.launch(Dispatchers.IO) {
            val catList = CatDatabase(getApplication()).catDao().getAllCat()
            showCats(catList)
        }
    }

    fun showCats(favoritesCatList: List<Cats>) {
        cats.postValue(favoritesCatList)
    }
}