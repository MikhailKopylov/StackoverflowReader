package com.amk.stackoverflowreader.mvp.view.listQuestion

import com.amk.stackoverflowreader.mvp.view.ItemView


interface QuestionItemView : ItemView {
    fun setQuestionBody(body: String)
    fun loadAvatar(url: String)
    fun setVotesCount(votes: Int)
    fun setAnswersCount(answers: Int)
    fun setViewsCount(views: Int)

}