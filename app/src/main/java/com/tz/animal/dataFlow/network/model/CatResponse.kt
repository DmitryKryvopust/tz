package com.tz.animal.dataFlow.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatResponse(
    val data: List<DataItem>?,
    val message: String = ""
) : Parcelable {
    @Parcelize
    data class DataItem(
        val title: String? = "",
        val url: String? = ""
    ) : Parcelable
}