package com.amk.stackoverflowreader.mvp.model.repository

import com.amk.stackoverflowreader.mvp.model.api.IDataSource
import com.amk.stackoverflowreader.mvp.model.api.OrderBy
import com.amk.stackoverflowreader.mvp.model.api.SortedForQuestion
import com.amk.stackoverflowreader.mvp.model.entity.question.RequestQuestion
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IQuestionRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class QuestionRepository(
    private val api: IDataSource,
) : IQuestionRepo {


    override fun getFindQuestions(
        query: String,
        sortForQuestion: SortedForQuestion,
        orderBy: OrderBy
    ): Single<RequestQuestion> =
        api.getFindQuestions(query, sortForQuestion, orderBy).subscribeOn(Schedulers.io())

    override fun getQuestions(
        sortForQuestion: SortedForQuestion,
        orderBy: OrderBy
    ): Single<RequestQuestion> =
        api.getAllQuestions(sortForQuestion, orderBy).subscribeOn(Schedulers.io())

}