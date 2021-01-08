package com.amk.stackoverflowreader.model.repository

import com.amk.stackoverflowreader.model.api.DataSource
import io.reactivex.rxjava3.schedulers.Schedulers

class StackUserRepository(val dataSource: DataSource) {

    fun getUser(userID: String) = dataSource.getUser(userID).subscribeOn(Schedulers.io())
}