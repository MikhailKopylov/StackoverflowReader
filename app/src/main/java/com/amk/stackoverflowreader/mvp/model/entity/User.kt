package com.amk.stackoverflowreader.mvp.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Long,
    var login: String,
) : Parcelable