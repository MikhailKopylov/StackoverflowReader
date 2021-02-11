package com.amk.stackoverflowreader.di

import com.amk.stackoverflowreader.di.answer.AnswerSubcomponent
import com.amk.stackoverflowreader.di.modules.ApiModule
import com.amk.stackoverflowreader.di.modules.AppModule
import com.amk.stackoverflowreader.di.modules.CiceroneModule
import com.amk.stackoverflowreader.di.question.QuestionSubcomponent
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        AppModule::class,
        ApiModule::class,
    ]
)
interface AppComponent {
    fun questionSubcomponent(): QuestionSubcomponent
    fun answerSubcomponent(): AnswerSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}