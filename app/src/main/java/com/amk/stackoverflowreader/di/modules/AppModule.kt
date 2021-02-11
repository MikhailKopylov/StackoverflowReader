package com.amk.stackoverflowreader.di.modules

import com.amk.stackoverflowreader.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}