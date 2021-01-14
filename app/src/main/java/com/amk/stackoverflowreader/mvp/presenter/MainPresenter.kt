package com.amk.stackoverflowreader.mvp.presenter

import com.amk.stackoverflowreader.navigation.Screens
import com.amk.stackoverflowreader.mvp.view.MainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router):MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked(){
        router.exit()
    }
}