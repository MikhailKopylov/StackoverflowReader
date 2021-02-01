package com.amk.stackoverflowreader.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class ListQuestion (
    @Expose val items:List<Question>
    ): Parcelable