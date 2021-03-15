package com.tz.animal.dataFlow

import com.tz.animal.dataFlow.network.model.CatResponse
import com.tz.animal.dataFlow.network.model.DogResponse
import io.reactivex.Single

interface IDataManager {
    fun getCats(
        onSuccess: (response : CatResponse) -> Unit,
        onFailure: (messageError: Throwable) -> Unit
    )

    fun getDogs(
        onSuccess: (response : DogResponse) -> Unit,
        onFailure: (messageError: Throwable) -> Unit
    )
}
