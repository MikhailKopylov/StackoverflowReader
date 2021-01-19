package com.amk.stackoverflowreader.mvp.view

import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView{
    fun getMAinPresenter():MainPresenter
}