package com.amk.stackoverflowreader.navigation

import com.amk.stackoverflowreader.mvp.model.entity.question.Question
import com.amk.stackoverflowreader.mvp.model.entity.user.User
import com.amk.stackoverflowreader.ui.fragments.answerFragment.AnswerFragment
import com.amk.stackoverflowreader.ui.fragments.loginFragment.LoginFragment
import com.amk.stackoverflowreader.ui.fragments.questionListFragment.QuestionListFragment
import com.amk.stackoverflowreader.ui.fragments.userFragment.UserFragment
import com.amk.stackoverflowreader.ui.fragments.userListFragment.UserListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class QuestionScreen : SupportAppScreen() {
        override fun getFragment() = QuestionListFragment.newInstance()
    }

    class ListAnswerScreen(private val question: Question) : SupportAppScreen() {
        override fun getFragment() = AnswerFragment.newInstance(question)
    }

    class UserListScreen : SupportAppScreen() {
        override fun getFragment() = UserListFragment.newInstance()
    }

    class UserScreen(val user: User) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class LoginScreen : SupportAppScreen() {
        override fun getFragment() = LoginFragment.newInstance()
    }

}