package com.amk.stackoverflowreader.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

// https://api.stackexchange.com/


@Parcelize
data class UserAccount(
//    val badgeCounts: BadgeCounts = BadgeCounts(0, 0, 0),
        @Expose val accountId: String,
        @Expose val userId: String,
        @Expose val displayName: String,
//    val isEmployee: Boolean = false,
//    val lastModifiedDate: Long = 0L,
//    val lastAccessDate: Long = 0L,
//    val reputationChangeYear: Int = 0,
//    val reputationChangeQuarter: Int = 0,
//    val reputationChangeMonth: Int = 0,
//    val reputationChangeWeek: Int = 0,
//    val reputationChangeDay: Int = 0,
//    val reputation: Int = 0,
//    val creationDate: Long,
//    val userType: String,
//    val acceptRate: Int,
//    val websiteUrl: String = "",
//    val link: String,
//    val profileImage: String,
) : Parcelable