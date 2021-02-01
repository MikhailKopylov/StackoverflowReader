package com.amk.stackoverflowreader.navigation

import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.ui.fragments.questionListFragment.QuestionListFragment
import com.amk.stackoverflowreader.ui.fragments.userFragment.UserFragment
import com.amk.stackoverflowreader.ui.fragments.userListFragment.UserListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class ListUserScreen(private val mainPresenter: MainPresenter) : SupportAppScreen() {
        override fun getFragment() = UserListFragment.newInstance(mainPresenter)
    }

    class UserScreen(private val mainPresenter: MainPresenter) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(mainPresenter)
    }

    class QuestionScreen(private val mainPresenter: MainPresenter) : SupportAppScreen() {
        override fun getFragment() = QuestionListFragment.newInstance(mainPresenter)
    }

}