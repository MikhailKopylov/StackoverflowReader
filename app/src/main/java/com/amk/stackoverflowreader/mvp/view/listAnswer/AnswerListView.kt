package com.amk.stackoverflowreader.mvp.view.listAnswer

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AnswerListView : MvpView {

    fun init()
    fun updateData()
    fun showClick(pos: Int)
    fun showQuestion(questionId: Long)

    fun setQuotaRemaining(quotaRemaining: Int)
    fun setHasMore(hasMore: Boolean)

}