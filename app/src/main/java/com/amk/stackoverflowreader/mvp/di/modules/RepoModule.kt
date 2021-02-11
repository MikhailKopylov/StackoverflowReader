package com.amk.stackoverflowreader.mvp.di.modules

import com.amk.stackoverflowreader.mvp.model.api.IDataSource
import com.amk.stackoverflowreader.mvp.model.repository.AnswerRepository
import com.amk.stackoverflowreader.mvp.model.repository.QuestionRepository
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IAnswerRepo
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IQuestionRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun questionRepo(api:IDataSource): IQuestionRepo = QuestionRepository(api)

    @Singleton
    @Provides
    fun answerRepo(api:IDataSource): IAnswerRepo = AnswerRepository(api)


}