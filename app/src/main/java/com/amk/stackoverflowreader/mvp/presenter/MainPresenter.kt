package com.amk.stackoverflowreader.mvp.presenter

import com.amk.stackoverflowreader.mvp.view.MainView
import com.amk.stackoverflowreader.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.QuestionScreen())
    }

    fun pressedBackButton() {
        router.exit()
    }


}