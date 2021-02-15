package com.amk.stackoverflowreader.mvp.model.repository.interfaces

import com.amk.stackoverflowreader.mvp.model.api.OrderBy
import com.amk.stackoverflowreader.mvp.model.api.SortedForUser
import com.amk.stackoverflowreader.mvp.model.entity.user.RequestUser
import io.reactivex.rxjava3.core.Single

interface IUserRepo {
    fun getAllUsers(sortForUser: SortedForUser, orderBy: OrderBy): Single<RequestUser>
    fun getFilterUsers(
        inNameUser: String,
        sortForUser: SortedForUser,
        orderBy: OrderBy
    ): Single<RequestUser>
}