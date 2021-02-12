package com.amk.stackoverflowreader.mvp.model.api

import com.amk.stackoverflowreader.mvp.model.entity.answer.RequestAnswer
import com.amk.stackoverflowreader.mvp.model.entity.question.RequestQuestion
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDataSource {
    @GET("/2.2/questions?order=desc&site=stackoverflow")
    fun getAllQuestions(@Query("sort") sortBy:SortedBy): Single<RequestQuestion>

    //    @GET("/2.2/search/excerpts?order=desc&sort=relevance&site=stackoverflow")
    @GET("/2.2/search/advanced?order=desc&site=stackoverflow")
    fun getFindQuestions(@Query("q") querySearch: String, @Query("sort") sortBy:SortedBy): Single<RequestQuestion>

    //    @GET("/2.2/questions/{questionId}/answers?order=desc&sort=activity&site=stackoverflow")
//    @GET("/2.2/questions/{questionId}/answers?order=desc&sort=votes&site=stackoverflow//Сортировка по числу голосов
    @GET("/2.2/questions/{questionId}?order=desc&sort=votes&site=stackoverflow")
    fun getAnswersSortVotes(@Path("questionId") questionId: Long): Single<RequestAnswer>

}