package com.amk.stackoverflowreader.mvp.view.question

import com.amk.stackoverflowreader.mvp.view.ItemView


interface QuestionItemView : ItemView {
    fun setQuestionBody(body: String)
}