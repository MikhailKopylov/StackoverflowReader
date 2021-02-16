package com.amk.stackoverflowreader.mvp.view.listUser

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface UserListView : MvpView {
    fun init()
    fun updateData()
    fun showClick(pos: Int)
    fun hideLoadUsers()
    fun release()
}