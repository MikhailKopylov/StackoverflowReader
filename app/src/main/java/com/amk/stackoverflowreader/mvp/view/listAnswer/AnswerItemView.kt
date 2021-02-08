package com.amk.stackoverflowreader.mvp.view.listAnswer

import com.amk.stackoverflowreader.mvp.view.ItemView


interface AnswerItemView : ItemView {
    fun setIsAccepted(isAccepted: Boolean)
    fun setScore(score: Int)
    fun setAnswerId(answerId: Long)
    fun setQuestionId(questionId: Long)
    fun setContentLicense(contentLicense: String)
    fun setOwnerName(displayName: String)


}