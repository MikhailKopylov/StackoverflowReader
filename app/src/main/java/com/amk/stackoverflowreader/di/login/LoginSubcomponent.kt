package com.amk.stackoverflowreader.di.login

import com.amk.stackoverflowreader.di.answer.AnswerSubcomponent
import com.amk.stackoverflowreader.mvp.presenter.listQuestion.QuestionListPresenter
import com.amk.stackoverflowreader.mvp.presenter.login.LoginPresenter
import dagger.Subcomponent

@LoginScope
@Subcomponent(
    modules = [
        LoginModule::class
    ]
)
interface LoginSubcomponent {


    fun inject(loginPresenter: LoginPresenter)
}
