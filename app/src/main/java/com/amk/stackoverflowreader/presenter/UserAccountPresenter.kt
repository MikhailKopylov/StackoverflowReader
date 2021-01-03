package com.amk.stackoverflowreader.presenter

import com.amk.stackoverflowreader.model.StackoverflowRepository
import com.amk.stackoverflowreader.view.UserAccountView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserAccountPresenter(private val view: UserAccountView) {

    private val model = StackoverflowRepository()

    fun clickUpdateUser() {
        model.getRandomUser()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.setAccountID(it.accountID.toString())
                view.setUserID(it.userID.toString())
                view.setName(it.displayName)
            }
    }

}