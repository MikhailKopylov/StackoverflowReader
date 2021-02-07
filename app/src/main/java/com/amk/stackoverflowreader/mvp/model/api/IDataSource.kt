package com.amk.stackoverflowreader.mvp.model.api

import com.amk.stackoverflowreader.mvp.model.entity.Answer
import com.amk.stackoverflowreader.mvp.model.entity.ListQuestion
import com.amk.stackoverflowreader.mvp.model.entity.Question
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IDataSource {
    @GET("/2.2/questions?order=desc&sort=activity&site=stackoverflow")
    fun getAllQuestions(): Single<ListQuestion>

    @GET("/2.2/search/excerpts?order=desc&sort=relevance&site=stackoverflow")
    fun getFindQuestions(@Query("q") querySearch:String):Single<ListQuestion>

//    @GET
//    fun getAnswers(@Url url: String): Single<List<Answer>>
}