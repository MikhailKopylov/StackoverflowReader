package com.amk.stackoverflowreader.mvp.model.repository

import com.amk.stackoverflowreader.mvp.model.api.IDataSource
import com.amk.stackoverflowreader.mvp.model.entity.answer.RequestAnswer
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IAnswerRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class AnswerRepository(
    private val api: IDataSource,
) : IAnswerRepo {

    override fun getAnswers(questionId: Long): Single<RequestAnswer> =
        api.getAnswers(questionId).subscribeOn(Schedulers.io())
}