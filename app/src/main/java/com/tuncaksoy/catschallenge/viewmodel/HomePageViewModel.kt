package com.tuncaksoy.catschallenge.viewmodel

import android.app.Application
import android.content.pm.ChangedPackages
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tuncaksoy.catschallenge.model.Cats
import com.tuncaksoy.catschallenge.servis.CatAPIServis
import com.tuncaksoy.catschallenge.servis.CatDatabase
import com.tuncaksoy.catschallenge.util.CatSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.ArrayList

open class HomePageViewModel(application: Application) : BaseViewModel(application) {
    val cats = MutableLiveData<List<Cats>>()
    private val CatApiServis = CatAPIServis()
    private val disposable = CompositeDisposable()
    private val catSharedPreferences = CatSharedPreferences(getApplication())
    var positionId: Int? = null
    var changedPositon: Int? = null
    var positionGenus: String? = null
    var positionFavorites: Boolean? = null

    private val _catss =
        MutableStateFlow(HomeUiState(onFavoritesChanged = { catId, catGenus, catFavorites ->
            viewModelScope.launch {
                positionGenus = catGenus
                positionId = catId
                positionFavorites = catFavorites
                println(positionId)
                println(changedPositon)
                getDataAPI()

            }
        }))

    val catss: StateFlow<HomeUiState> = _catss.asStateFlow()


    fun refreshData() {
        getDataAPI()
    }

    private fun getDataAPI() {
        disposable.add(
            CatApiServis.getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Cats>>() {

                    override fun onSuccess(t: List<Cats>) {
                        if (positionId != null && positionGenus != null) {
                            if (changedPositon != positionId) {
                                putSQLITE(t, positionId!!, positionGenus!!, positionFavorites!!)
                                changedPositon = positionId
                            }
                        }
                        showCats(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showCats(catsList: List<Cats>) {
        cats.postValue(catsList)
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
        catSharedPreferences.saveTime(System.nanoTime())
    }

}

data class HomeUiState(
    val onFavoritesChanged: (Int?, String?, Boolean?) -> Unit
)