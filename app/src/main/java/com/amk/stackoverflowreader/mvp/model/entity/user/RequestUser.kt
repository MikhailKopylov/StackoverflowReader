package com.amk.stackoverflowreader.mvp.model.entity.user

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class RequestUser(
    @Expose val items: List<User>
) : Parcelable