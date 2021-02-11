package com.amk.stackoverflowreader.di.answer

import com.amk.stackoverflowreader.mvp.presenter.answer.AnswerPresenter
import dagger.Subcomponent

@AnswerScope
@Subcomponent(
    modules = [
        AnswerModule::class
    ]
)

interface AnswerSubcomponent {

    fun inject(answerRepository: AnswerPresenter)
}