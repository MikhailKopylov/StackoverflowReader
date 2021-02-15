package com.amk.stackoverflowreader.mvp.model.api

import com.amk.stackoverflowreader.mvp.model.entity.answer.RequestAnswer
import com.amk.stackoverflowreader.mvp.model.entity.question.RequestQuestion
import com.amk.stackoverflowreader.mvp.model.entity.user.RequestUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDataSource {
    @GET("/2.2/questions?site=stackoverflow")
    fun getAllQuestions(
        @Query("sort") sortForQuestion: SortedForQuestion,
        @Query("order") orderBy: OrderBy
    ): Single<RequestQuestion>

    //    @GET("/2.2/search/excerpts?order=desc&sort=relevance&site=stackoverflow")
    @GET("/2.2/search/advanced?site=stackoverflow")
    fun getFindQuestions(
        @Query("q") querySearch: String,
        @Query("sort") sortForQuestion: SortedForQuestion,
        @Query("order") orderBy: OrderBy
    ): Single<RequestQuestion>

    //    @GET("/2.2/questions/{questionId}/answers?order=desc&sort=activity&site=stackoverflow")
//    @GET("/2.2/questions/{questionId}/answers?order=desc&sort=votes&site=stackoverflow//Сортировка по числу голосов
    @GET("/2.2/questions/{questionId}?order=desc&sort=votes&site=stackoverflow")
    fun getAnswersSortVotes(@Path("questionId") questionId: Long): Single<RequestAnswer>

    @GET("/2.2/users?site=stackoverflow")
    fun getAllUsers(
        @Query("sort") sortForUser: SortedForUser,
        @Query("order") orderBy: OrderBy
    ): Single<RequestUser>

    @GET("/2.2/users?site=stackoverflow")
    fun getFilterUsers(
        @Query("inname") inName: String,
        @Query("sort") sortForUser: SortedForUser,
        @Query("order") orderBy: OrderBy
    ): Single<RequestUser>


}