package com.amk.stackoverflowreader.mvp.model.repository

import com.amk.stackoverflowreader.mvp.model.api.IDataSource
import com.amk.stackoverflowreader.mvp.model.api.OrderBy
import com.amk.stackoverflowreader.mvp.model.api.SortedBy
import com.amk.stackoverflowreader.mvp.model.entity.question.RequestQuestion
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IQuestionRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class QuestionRepository(
    private val api: IDataSource,
) : IQuestionRepo {


    override fun getFindQuestions(query: String, sortBy: SortedBy, orderBy: OrderBy): Single<RequestQuestion> =
        api.getFindQuestions(query, sortBy, orderBy).subscribeOn(Schedulers.io())

    override fun getQuestions(sortBy: SortedBy, orderBy: OrderBy): Single<RequestQuestion> =
        api.getAllQuestions(sortBy, orderBy).subscribeOn(Schedulers.io())

}