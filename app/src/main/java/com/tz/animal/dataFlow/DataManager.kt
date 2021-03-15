package com.tz.animal.dataFlow

import com.tz.animal.dataFlow.network.Api
import com.tz.animal.dataFlow.network.model.CatResponse
import com.tz.animal.dataFlow.network.model.DogResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataManager constructor(private val api: Api) :
    IDataManager {
    override fun getCats(onSuccess: (response : CatResponse) -> Unit, onFailure: (messageError: Throwable) -> Unit) {
        val request = api.getCats("cat").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, { error ->
                onFailure(error)
            })
    }

    override fun getDogs(
        onSuccess: (response:DogResponse) -> Unit,
        onFailure: (messageError: Throwable) -> Unit
    ) {
        val request = api.getDogs("dog").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, { error ->
                onFailure(error)
            })
    }


}