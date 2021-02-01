package com.amk.stackoverflowreader.mvp.presenter

import android.util.Log
import com.amk.stackoverflowreader.mvp.model.entity.User
import com.amk.stackoverflowreader.mvp.model.repository.UserRepository
import com.amk.stackoverflowreader.mvp.view.MainView
import com.amk.stackoverflowreader.navigation.Screens
import com.amk.stackoverflowreader.ui.activities.TAG
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {


    var selectedUser: User? = null
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.QuestionScreen(this))
    }

    fun pressedBackButton() {
        router.exit()
    }

    fun startUserScreen(id: Long) {
        UserRepository
            .getUsers()
            .filter { it.id == id }
            .take(1)
            .subscribe({
                selectedUser = it
                router.navigateTo(Screens.UserScreen(this))
            }, {
                Log.d(TAG, it.toString())
            })
    }
}