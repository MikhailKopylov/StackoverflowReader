package com.amk.stackoverflowreader.mvp.model.entity.answer

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class RequestAnswer(
    @Expose val items: List<Answer>,
    @Expose val hasMore: Boolean,
    @Expose val quotaMax: Int,
    @Expose val quotaRemaining: Int,
) : Parcelable