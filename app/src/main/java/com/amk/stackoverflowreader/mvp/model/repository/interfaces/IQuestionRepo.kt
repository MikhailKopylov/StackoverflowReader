package com.amk.stackoverflowreader.mvp.model.repository.interfaces

import com.amk.stackoverflowreader.mvp.model.api.OrderBy
import com.amk.stackoverflowreader.mvp.model.api.SortedBy
import com.amk.stackoverflowreader.mvp.model.entity.question.RequestQuestion
import io.reactivex.rxjava3.core.Single

interface IQuestionRepo {
    fun getFindQuestions(query: String, sortBy: SortedBy, orderBy: OrderBy): Single<RequestQuestion>
    fun getQuestions(sortBy: SortedBy, orderBy: OrderBy): Single<RequestQuestion>
}