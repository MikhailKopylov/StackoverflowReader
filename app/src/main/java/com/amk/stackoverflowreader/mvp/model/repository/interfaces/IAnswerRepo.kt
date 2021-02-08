package com.amk.stackoverflowreader.mvp.model.repository.interfaces

import com.amk.stackoverflowreader.mvp.model.entity.answer.RequestAnswer
import io.reactivex.rxjava3.core.Single

interface IAnswerRepo {
    fun getAnswers(questionId: Long): Single<RequestAnswer>
}