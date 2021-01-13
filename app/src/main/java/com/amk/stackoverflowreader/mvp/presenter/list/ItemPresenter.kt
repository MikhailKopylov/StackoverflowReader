package com.amk.stackoverflowreader.mvp.presenter.list

import com.amk.stackoverflowreader.mvp.view.list.ItemView
import com.amk.stackoverflowreader.mvp.view.list.UserItemView

interface ItemPresenter<V : ItemView> {
    var itemClickListener: ((UserItemView) -> Unit)?

    fun getCount():Int
    fun bindView(view: V)
}