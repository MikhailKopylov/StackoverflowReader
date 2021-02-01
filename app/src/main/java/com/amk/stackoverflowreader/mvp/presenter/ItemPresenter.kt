package com.amk.stackoverflowreader.mvp.presenter

import com.amk.stackoverflowreader.mvp.view.ItemView
import com.amk.stackoverflowreader.mvp.view.listUser.UserItemView

interface ItemPresenter<V : ItemView> {
    val itemClickListener: ((ItemView) -> Unit)

    fun getCount(): Int
    fun bindView(view: V)
}