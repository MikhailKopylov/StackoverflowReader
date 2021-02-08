package com.amk.stackoverflowreader.mvp.presenter.user

import com.amk.stackoverflowreader.mvp.model.entity.User
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.view.user.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val user: User, private val router: Router) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        init()
    }

    private fun init() {
        setLogin()
    }

    private fun setLogin() {
        viewState.setLoginUser(user.login)
    }

    fun pressedBackButton(): Boolean {
        router.exit()
        return true
    }

}