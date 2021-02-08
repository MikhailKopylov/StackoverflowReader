package com.amk.stackoverflowreader.mvp.model.entity.answer

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class Owner(
    @Expose private val reputation: Int,
    @Expose private val userId: Long,
    @Expose private val userType: String,
    @Expose private val acceptRate: Int,
    @Expose private val profileImage: String,
    @Expose val displayName: String,
    @Expose private val link: String,
) : Parcelable