package com.tz.animal.dataFlow.network.model

data class DogResponse(
    val data: List<DataItem>?,
    val message: String = ""
) {
    data class DataItem(
        val title: String = "",
        val url: String = ""
    )
}