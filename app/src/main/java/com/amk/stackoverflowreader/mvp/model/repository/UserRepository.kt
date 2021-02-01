package com.amk.stackoverflowreader.mvp.model.repository

import com.amk.stackoverflowreader.mvp.model.entity.User
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IUserRepo
import io.reactivex.rxjava3.core.Observable

object UserRepository:IUserRepo {

    private val userList = listOf(
        User(1, "user1"),
        User(2, "user2"),
        User(3, "user3"),
        User(4, "user4"),
        User(5, "user5"),
        User(6, "user6"),
    )

    override fun getUsers(): Observable<User> = Observable.fromIterable(userList)
}