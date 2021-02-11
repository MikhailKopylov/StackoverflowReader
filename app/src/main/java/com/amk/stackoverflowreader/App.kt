package com.amk.stackoverflowreader

import android.app.Application
import com.amk.stackoverflowreader.mvp.di.AppComponent
import com.amk.stackoverflowreader.mvp.di.DaggerAppComponent
import com.amk.stackoverflowreader.mvp.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

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


}