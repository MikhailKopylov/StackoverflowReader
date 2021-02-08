package com.amk.stackoverflowreader.mvp.model.repository.interfaces

import com.amk.stackoverflowreader.mvp.model.entity.question.RequestQuestion
import io.reactivex.rxjava3.core.Single

interface IQuestionRepo {
    fun getFindQuestions(query: String): Single<RequestQuestion>
    fun getQuestions(): Single<RequestQuestion>
}