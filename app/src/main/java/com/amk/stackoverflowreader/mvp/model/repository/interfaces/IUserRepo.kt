package com.amk.stackoverflowreader.mvp.model.repository.interfaces

import com.amk.stackoverflowreader.mvp.model.entity.User
import io.reactivex.rxjava3.core.Observable

interface IUserRepo {
    fun getUsers(): Observable<User>
}