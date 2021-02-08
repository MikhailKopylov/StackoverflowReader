package com.amk.stackoverflowreader.navigation

import com.amk.stackoverflowreader.mvp.model.entity.User
import com.amk.stackoverflowreader.mvp.model.entity.question.Question
import com.amk.stackoverflowreader.ui.fragments.answerListFragment.AnswerListFragment
import com.amk.stackoverflowreader.ui.fragments.questionListFragment.QuestionListFragment
import com.amk.stackoverflowreader.ui.fragments.userFragment.UserFragment
import com.amk.stackoverflowreader.ui.fragments.userListFragment.UserListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class ListUserScreen : SupportAppScreen() {
        override fun getFragment() = UserListFragment.newInstance()
    }

    class UserScreen(private val user: User) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class QuestionScreen : SupportAppScreen() {
        override fun getFragment() = QuestionListFragment.newInstance()
    }

    class ListAnswerScreen(private val question: Question) : SupportAppScreen() {
        override fun getFragment() = AnswerListFragment.newInstance(question)
    }

}