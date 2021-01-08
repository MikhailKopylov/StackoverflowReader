package com.amk.stackoverflowreader.model.api

import com.amk.stackoverflowreader.model.entity.UserAccountList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DataSource {
    @GET("/2.2/users/{ids}?order=desc&sort=reputation&site=stackoverflow")
    fun getUser(@Path("ids") userId: String): Single<UserAccountList>
}