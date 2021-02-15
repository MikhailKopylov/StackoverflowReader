package com.amk.stackoverflowreader.mvp.model.entity.user

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @Expose val id: Long,
    @Expose val displayName: String,
    @Expose val link: String?,
    @Expose val profileImage: String?,
    @Expose val reputation: Int,
    @Expose val creationDate: Long,
    @Expose val lastModifiedDate: Long,
    @Expose val location: String,

    ) : Parcelable