package com.tz.animal.ui.fragments.dogs.adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DogDetailModel(val title : String, val url:String, val position: Int): Parcelable