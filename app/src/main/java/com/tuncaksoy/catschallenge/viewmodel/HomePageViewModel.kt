package com.tuncaksoy.catschallenge.viewmodel

import android.app.Application
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
import kotlinx.coroutines.launch

var deneme = 1

open class HomePageViewModel(application: Application) : BaseViewModel(application) {
    val cats = MutableLiveData<List<Cats>>()
    private val CatApiServis = CatAPIServis()
    private val disposable = CompositeDisposable()
    private val catSharedPreferences = CatSharedPreferences(getApplication())




    fun refreshData() {
        getDataAPI()
    }

    private fun getDataAPI() {
        disposable.add(
            CatApiServis.getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Cats>>() {

                    override fun onSuccess(t: List<Cats>) {
                        putSQLITE(t)
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


    fun putSQLITE(catsList: List<Cats>/*catId : Int*/) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = CatDatabase(getApplication()).catDao()
            dao.deleteAllCat()
            //dao.insert(catsList[catId])
            //dao.insertAll(cats)
            val uuidListesi = dao.insertAll(*catsList.toTypedArray())
            var i = 0
            while (i < catsList.size) {
                catsList[i].uuid = uuidListesi[i].toInt()
                i = i + 1
            }
        }
        catSharedPreferences.saveTime(System.nanoTime())
    }

}