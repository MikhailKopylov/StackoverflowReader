package com.amk.stackoverflowreader.di.question

import com.amk.stackoverflowreader.mvp.model.api.IDataSource
import com.amk.stackoverflowreader.mvp.model.repository.QuestionRepository
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IQuestionRepo
import dagger.Module
import dagger.Provides

@Module
open class QuestionModule {

    @QuestionScope
    @Provides
    fun questionRepo(api: IDataSource): IQuestionRepo = QuestionRepository(api)

}