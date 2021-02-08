package com.amk.stackoverflowreader.mvp.model.entity.question

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class RequestQuestion(
    @Expose val items: List<Question>
) : Parcelable