package com.tuncaksoy.catschallenge.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuncaksoy.catschallenge.model.Cats
import com.tuncaksoy.catschallenge.servis.CatDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DetailPageViewModel (application: Application) : BaseViewModel(application) {
    val catLiveData = MutableLiveData<Cats>()


    fun getRoomData(uuid: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val dao = CatDatabase(getApplication()).catDao()
            val cat = dao.getCat(uuid)
            catLiveData.postValue(cat)
        }
    }

}