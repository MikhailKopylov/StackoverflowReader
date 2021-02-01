package com.amk.stackoverflowreader.mvp.model.repository.interfaces

import com.amk.stackoverflowreader.mvp.model.entity.Answer
import com.amk.stackoverflowreader.mvp.model.entity.ListQuestion
import com.amk.stackoverflowreader.mvp.model.entity.Question
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface IQuestionRepo {

    fun getQuestions(query:String): Single<List<Question>>
    fun getQuestions(): Single<ListQuestion>
}