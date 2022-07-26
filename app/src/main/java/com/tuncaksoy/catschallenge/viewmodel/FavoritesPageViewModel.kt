package com.tuncaksoy.catschallenge.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tuncaksoy.catschallenge.model.Cats
import com.tuncaksoy.catschallenge.servis.CatDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class FavoritesPageViewModel(application: Application) : BaseViewModel(application) {
    val cats = MutableLiveData<List<Cats>>()
    var positionId: Int? = null
    var changedPositon: Int? = null
    var positionGenus: String? = null
    var positionFavorites: Boolean? = null
    lateinit var catList : List<Cats>
    private val __catss = MutableStateFlow(HomeUiState(onFavoritesChanged = { catId,catGenus,catFavorites ->
        viewModelScope.launch {
            positionGenus = catGenus
            positionId = catId
            positionFavorites = catFavorites
            println(positionGenus)
            if ( positionGenus != null) {
                if (changedPositon != positionId) {
                    putSQLITE(catList, positionId!!, positionGenus!!, positionFavorites!!)
                    changedPositon = positionId
                }
            }
        }
    }))
    val catss : StateFlow<HomeUiState> = __catss.asStateFlow()

    fun refreshData() {
        getDataSQLite()
    }

    private fun getDataSQLite() {
        viewModelScope.launch(Dispatchers.IO) {
            catList = CatDatabase(getApplication()).catDao().getAllCat()
            showCats(catList)
        }
    }

    fun showCats(favoritesCatList: List<Cats>) {
        cats.postValue(favoritesCatList)
    }

    fun putSQLITE(catsList: List<Cats>, catId: Int, catGenus: String, catFavorites: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = CatDatabase(getApplication()).catDao()
            Log.d("selam","catGenus" + catGenus)
            val favoritesList = dao.getAllGenus()
            var catFavoritess = false
            if (favoritesList.size!=0){
                var i = 0
                while(i<favoritesList.size) {
                    var favoritesGenus = favoritesList[i].catGenus
                    i++
                    Log.d("selam","favoritesGenusif->"+favoritesGenus)
                    if (catGenus == favoritesGenus) {
                        catFavoritess = true
                    }
                }
            }
            Log.d("selam","CatFavorites->" + catFavoritess.toString())
            //println(favoritesList)
            //dao.deleteAllCat()
            if (catFavoritess == true) {
                dao.deleteCat(catGenus)
                println("yokburada")
            } else {
                dao.insert(catsList[catId])
                println("burada")
            }
            //dao.insertAll(cats)
            /*val uuidListesi = dao.insertAll(*catsList.toTypedArray())
            var i = 0
            while (i < catsList.size) {
                catsList[i].uuid = uuidListesi[i].toInt()
                i = i + 1
            }*/
        }
        getDataSQLite()
    }
}

