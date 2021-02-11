package com.amk.stackoverflowreader.mvp.view.answer

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AnswerView : MvpView {

    fun showQuestionAndAnswers(questionId: Long)

    fun setQuotaRemaining(quotaRemaining: Int)
    fun setHasMore(hasMore: Boolean)

}