package com.amk.stackoverflowreader.presenter

import com.amk.stackoverflowreader.model.repository.StackUserRepository
import com.amk.stackoverflowreader.view.UserAccountView
import io.reactivex.rxjava3.core.Scheduler
import timber.log.Timber


class UserAccountPresenter(
    private val view: UserAccountView,
    val mainThreadScheduler: Scheduler,
    val userRepository: StackUserRepository,
) {


//    fun clickUpdateUser() {
//        model.getUser()
//        model.getRandomUser()
//            .subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                view.setAccountID(it.accountID.toString())
//                view.setUserID(it.userID.toString())
//                view.setName(it.displayName)
//            }
//    }

    fun loadUser(userIDString: String) {


//
        userRepository
            .getUser(userIDString)

            .subscribeOn(mainThreadScheduler)

            .subscribe({

                view.setName(it.items[0].displayName)
                view.setUserID(it.items[0].userId)
                view.setAccountID(it.items[0].accountId)
            }, {
                Timber.e(it)
            })
    }

}