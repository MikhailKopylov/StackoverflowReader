package com.amk.stackoverflowreader.mvp.view.question

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface QuestionListView :MvpView{

    fun init()
    fun updateData()
    fun showClick(pos: Int)


}