package com.amk.stackoverflowreader.di.answer

import com.amk.stackoverflowreader.mvp.model.api.IDataSource
import com.amk.stackoverflowreader.mvp.model.repository.AnswerRepository
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IAnswerRepo
import dagger.Module
import dagger.Provides

@Module
open class AnswerModule {

    @AnswerScope
    @Provides
    fun answerRepo(api: IDataSource): IAnswerRepo = AnswerRepository(api)
}