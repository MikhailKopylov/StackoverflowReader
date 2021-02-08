package com.amk.stackoverflowreader.mvp.view.listQuestion

import com.amk.stackoverflowreader.mvp.view.ItemView


interface QuestionItemView : ItemView {
    fun setQuestionBody(body: String)
}