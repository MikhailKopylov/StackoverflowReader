package com.amk.stackoverflowreader.mvp.model.repository.interfaces

import com.amk.stackoverflowreader.mvp.model.entity.Answer
import io.reactivex.rxjava3.core.Observable

interface IAnswerRepo {

    fun getAnswers():Observable<List<Answer>>
}