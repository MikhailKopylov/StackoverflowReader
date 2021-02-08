package com.amk.stackoverflowreader.mvp.model.api

import com.amk.stackoverflowreader.mvp.model.entity.answer.RequestAnswer
import com.amk.stackoverflowreader.mvp.model.entity.question.RequestQuestion
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDataSource {
    @GET("/2.2/questions?order=desc&sort=activity&site=stackoverflow")
    fun getAllQuestions(): Single<RequestQuestion>

    @GET("/2.2/search/excerpts?order=desc&sort=relevance&site=stackoverflow")
    fun getFindQuestions(@Query("q") querySearch: String): Single<RequestQuestion>

    @GET("/2.2/questions/{questionId}/answers?order=desc&sort=activity&site=stackoverflow")
    fun getAnswers(@Path("questionId") questionId: Long): Single<RequestAnswer>
}