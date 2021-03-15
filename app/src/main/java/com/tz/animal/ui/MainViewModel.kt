package com.tz.animal.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tz.animal.dataFlow.DataManager
import com.tz.animal.dataFlow.network.model.CatResponse
import com.tz.animal.dataFlow.network.model.DogResponse

class MainViewModel(private val dataManager: DataManager) : ViewModel() {

    private val cats: MutableLiveData<List<CatResponse.DataItem>> = MutableLiveData()
    private val dogs: MutableLiveData<List<DogResponse.DataItem>> = MutableLiveData()

    var catsScrollPosition: MutableLiveData<Int> = MutableLiveData(-1)
    var dogsScrollPosition: MutableLiveData<Int> = MutableLiveData(-1)
    fun cats() = cats
    fun dogs() = dogs

    fun getCats() {
        dataManager.getCats(
            onSuccess = {
                it.let {
                    cats.postValue(it.data)
                }
            },
            onFailure = {
                it.message
            }
        )
    }

    fun getDogs() {
        dataManager.getDogs(
            onSuccess = {
                it.let {
                    dogs.postValue(it.data)
                }
            },
            onFailure = {
                it.message
            }
        )
    }
}