package com.amk.stackoverflowreader.mvp.model.repository

import com.amk.stackoverflowreader.mvp.model.api.IDataSource
import com.amk.stackoverflowreader.mvp.model.entity.ListQuestion
import com.amk.stackoverflowreader.mvp.model.entity.Question
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IQuestionRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class QuestionRepository(
    private val api: IDataSource,
) : IQuestionRepo {

    override fun getFindQuestions(query: String): Single<ListQuestion> =
        api.getFindQuestions(query).subscribeOn(Schedulers.io())

    override fun getQuestions(): Single<ListQuestion> =
        api.getAllQuestions().subscribeOn(Schedulers.io())



}