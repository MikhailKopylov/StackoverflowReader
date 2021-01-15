package com.amk.stackoverflowreader.mvp.presenter

import com.amk.stackoverflowreader.mvp.model.User
import com.amk.stackoverflowreader.mvp.view.MainView
import com.amk.stackoverflowreader.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    var selectedUser: User? = null
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.ListUserScreen(this))
    }

    fun pressedBackButton() {
        router.exit()
    }

    fun startUserScreen(user: User) {
        selectedUser = user
        router.navigateTo(Screens.UserScreen(this))
    }
}