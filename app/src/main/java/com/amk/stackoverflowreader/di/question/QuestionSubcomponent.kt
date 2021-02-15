package com.amk.stackoverflowreader.di.question

import com.amk.stackoverflowreader.di.answer.AnswerSubcomponent
import com.amk.stackoverflowreader.mvp.presenter.listQuestion.QuestionListPresenter
import dagger.Subcomponent

@QuestionScope
@Subcomponent(
    modules = [
        QuestionModule::class
    ]
)
interface QuestionSubcomponent {

    fun answerSubcomponent(): AnswerSubcomponent

    fun inject(questionPresenter: QuestionListPresenter)
}
