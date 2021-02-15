package com.amk.stackoverflowreader.mvp.model.repository

import com.amk.stackoverflowreader.mvp.model.api.IDataSource
import com.amk.stackoverflowreader.mvp.model.api.OrderBy
import com.amk.stackoverflowreader.mvp.model.api.SortedForUser
import com.amk.stackoverflowreader.mvp.model.entity.user.RequestUser
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IUserRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepository(private val api: IDataSource) : IUserRepo {

    override fun getAllUsers(sortForUser: SortedForUser, orderBy: OrderBy): Single<RequestUser> =
        api.getAllUsers(sortForUser, orderBy).subscribeOn(Schedulers.io())

    override fun getFilterUsers(
        inNameUser: String,
        sortForUser: SortedForUser,
        orderBy: OrderBy
    ): Single<RequestUser> =
        api.getFilterUsers(inNameUser, sortForUser, orderBy).subscribeOn(Schedulers.io())


}