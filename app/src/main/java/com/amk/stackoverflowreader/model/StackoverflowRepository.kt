package com.amk.stackoverflowreader.model

import io.reactivex.rxjava3.core.Observable

class StackoverflowRepository {

    private var index = 0
    private val userAccountList = mutableListOf(
        UserAccount(11948, 23283, "JaredPar"),
        UserAccount(90257, 248521, "jax"),
        UserAccount(53798, 160887, "Jonathan M Davis"),
        UserAccount(6386201, 4954037, "hiro protagonist"),
        UserAccount(30802, 84206, "AaronLS"),
    )

    fun getRandomUser(): Observable<UserAccount> {
        return Observable.create {
            if (index >= userAccountList.size) {
                index = 0
            }
            it.onNext(userAccountList[index++])
        }
    }
}