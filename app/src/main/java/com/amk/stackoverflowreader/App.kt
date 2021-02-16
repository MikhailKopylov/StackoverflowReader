package com.amk.stackoverflowreader

import android.app.Application
import com.amk.stackoverflowreader.di.AppComponent
import com.amk.stackoverflowreader.di.DaggerAppComponent
import com.amk.stackoverflowreader.di.answer.AnswerSubcomponent
import com.amk.stackoverflowreader.di.login.LoginSubcomponent
import com.amk.stackoverflowreader.di.modules.AppModule
import com.amk.stackoverflowreader.di.question.QuestionSubcomponent
import com.amk.stackoverflowreader.di.user.UserSubcomponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    var questionSubcomponent: QuestionSubcomponent? = null
    var answerSubcomponent: AnswerSubcomponent? = null
    var userSubcomponent: UserSubcomponent? = null
    var loginSubcomponent: LoginSubcomponent? = null

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

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun releaseUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = null
    }

    fun initLoginSubcomponent() = appComponent.loginSubcomponent().also {
        loginSubcomponent = it
    }

    fun releaseLoginSubcomponent() = appComponent.loginSubcomponent().also {
        loginSubcomponent = null
    }


}