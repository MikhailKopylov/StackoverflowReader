package com.amk.stackoverflowreader

import android.app.Application
import com.amk.stackoverflowreader.di.AppComponent
import com.amk.stackoverflowreader.di.DaggerAppComponent
import com.amk.stackoverflowreader.di.answer.AnswerSubcomponent
import com.amk.stackoverflowreader.di.modules.AppModule
import com.amk.stackoverflowreader.di.question.QuestionSubcomponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    var questionSubcomponent: QuestionSubcomponent? = null
    var answerSubcomponent: AnswerSubcomponent? = null

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initQuestionSubcomponent() = appComponent.questionSubcomponent().also {
        questionSubcomponent = it
    }

    fun releaseQuestionSubcomponent() = appComponent.questionSubcomponent().also {
        questionSubcomponent = null
    }

    fun initAnswerSubcomponent() = appComponent.answerSubcomponent().also {
        answerSubcomponent = it
    }

    fun releaseAnswerSubcomponent() = appComponent.answerSubcomponent().also {
        answerSubcomponent = null
    }


}