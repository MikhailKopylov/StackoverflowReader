package com.amk.stackoverflowreader.mvp.model.repository.interfaces

import com.amk.stackoverflowreader.mvp.model.api.OrderBy
import com.amk.stackoverflowreader.mvp.model.api.SortedForQuestion
import com.amk.stackoverflowreader.mvp.model.entity.question.RequestQuestion
import io.reactivex.rxjava3.core.Single

interface IQuestionRepo {
    fun getFindQuestions(
        query: String,
        sortForQuestion: SortedForQuestion,
        orderBy: OrderBy
    ): Single<RequestQuestion>

    fun getQuestions(sortForQuestion: SortedForQuestion, orderBy: OrderBy): Single<RequestQuestion>
}