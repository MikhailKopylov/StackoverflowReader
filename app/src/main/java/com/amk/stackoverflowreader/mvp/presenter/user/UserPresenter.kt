package com.amk.stackoverflowreader.mvp.presenter.user

import com.amk.stackoverflowreader.mvp.model.entity.user.User
import com.amk.stackoverflowreader.mvp.view.user.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter(
    private val user: User,
) : MvpPresenter<UserView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        users()
    }

    private fun users() {
        user.link?.let { viewState.showUser(user.link) }
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