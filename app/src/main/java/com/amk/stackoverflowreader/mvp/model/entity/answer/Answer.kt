package com.amk.stackoverflowreader.mvp.model.entity.answer

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class Answer(
    @Expose val owner: Owner,
    @Expose val isAccepted: Boolean,
    @Expose val score: Int,
    @Expose val lastActivityDate: Long,
    @Expose val creationDate: Long,
    @Expose val answerId: Long,
    @Expose val questionId: Long,
    @Expose val contentLicense: String,
) : Parcelable