package com.amk.stackoverflowreader.mvp.presenter.list

import com.amk.stackoverflowreader.mvp.view.list.ItemView

interface ItemPresenter<V : ItemView> {
    var itemClickListener:((V) -> Unit)

    fun getCount():Int
    fun bindView(view: V)
}