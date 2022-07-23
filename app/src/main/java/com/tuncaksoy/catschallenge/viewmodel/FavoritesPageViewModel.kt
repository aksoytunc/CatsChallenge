package com.tuncaksoy.catschallenge.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tuncaksoy.catschallenge.model.Cats
import com.tuncaksoy.catschallenge.servis.CatDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class FavoritesPageViewModel(application: Application) : BaseViewModel(application) {
    val cats = MutableLiveData<List<Cats>>()

    private val __catss = MutableStateFlow(HomeUiState(onFavoritesChanged = { catId,catGenus ->
        viewModelScope.launch {
            val deneme = catGenus
            println(deneme)
        }
    }))
    val catss : StateFlow<HomeUiState> = __catss.asStateFlow()

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
