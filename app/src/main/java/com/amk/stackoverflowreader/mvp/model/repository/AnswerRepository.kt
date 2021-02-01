package com.amk.stackoverflowreader.mvp.model.repository

import com.amk.stackoverflowreader.mvp.model.entity.Answer
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IAnswerRepo
import io.reactivex.rxjava3.core.Observable

class AnswerRepository: IAnswerRepo {
    override fun getAnswers(): Observable<List<Answer>> {
        TODO("Not yet implemented")
    }
}