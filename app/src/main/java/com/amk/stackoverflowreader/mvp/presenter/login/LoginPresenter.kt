package com.amk.stackoverflowreader.mvp.presenter.login

import com.amk.stackoverflowreader.mvp.view.login.LoginView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class LoginPresenter : MvpPresenter<LoginView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.accessPermission()
    }

    fun pressedBackButton(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }


}