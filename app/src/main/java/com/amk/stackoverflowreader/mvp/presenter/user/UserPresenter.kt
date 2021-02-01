package com.amk.stackoverflowreader.mvp.presenter.user

import com.amk.stackoverflowreader.mvp.model.entity.User
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.view.user.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val router: Router) : MvpPresenter<UserView>() {

    lateinit var mainPresenter: MainPresenter
    private lateinit var user: User

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        init()
    }

    private fun init() {
        user = mainPresenter.selectedUser ?: User(-1, "not find user")
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