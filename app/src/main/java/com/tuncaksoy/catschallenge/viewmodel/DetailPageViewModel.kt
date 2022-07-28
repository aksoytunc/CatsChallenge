package com.tuncaksoy.catschallenge.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuncaksoy.catschallenge.model.Cats
import com.tuncaksoy.catschallenge.servis.CatAPIServis
import com.tuncaksoy.catschallenge.servis.CatDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DetailPageViewModel (application: Application) : BaseViewModel(application) {
    val catLiveData = MutableLiveData<Cats>()
    private val CatApiServis = CatAPIServis()
    private val disposable = CompositeDisposable()

    fun getRoomData(uuid: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val dao = CatDatabase(getApplication()).catDao()
            val cat = dao.getCat(uuid)
            catLiveData.postValue(cat)
        }
    }
    fun getDataAPI(position : Int) {
        disposable.add(
            CatApiServis.getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Cats>>() {

                    override fun onSuccess(t: List<Cats>) {
                        catLiveData.postValue(t[position])
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

}