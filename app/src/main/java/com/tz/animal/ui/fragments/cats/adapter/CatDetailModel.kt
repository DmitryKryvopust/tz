package com.tz.animal.ui.fragments.cats.adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatDetailModel(val title : String, val url:String, val position: Int): Parcelable