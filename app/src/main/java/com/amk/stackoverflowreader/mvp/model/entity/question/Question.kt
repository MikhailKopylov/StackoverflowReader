package com.amk.stackoverflowreader.mvp.model.entity.question

import android.os.Parcelable
import com.amk.stackoverflowreader.mvp.model.entity.answer.Owner
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class Question(
    @Expose val title: String,
    @Expose val questionId: Long,
    @Expose val owner: Owner,
) : Parcelable