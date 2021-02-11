package com.amk.stackoverflowreader.mvp.di

import com.amk.stackoverflowreader.mvp.di.modules.ApiModule
import com.amk.stackoverflowreader.mvp.di.modules.AppModule
import com.amk.stackoverflowreader.mvp.di.modules.CiceroneModule
import com.amk.stackoverflowreader.mvp.di.modules.RepoModule
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.presenter.answer.AnswerPresenter
import com.amk.stackoverflowreader.mvp.presenter.listQuestion.QuestionListPresenter
import com.amk.stackoverflowreader.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        AppModule::class,
        ApiModule::class,
        RepoModule::class,
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(answerRepository: AnswerPresenter)
    fun inject(questionRepository: QuestionListPresenter)

}