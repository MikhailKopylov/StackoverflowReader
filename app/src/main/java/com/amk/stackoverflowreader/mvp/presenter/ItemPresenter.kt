package com.amk.stackoverflowreader.mvp.presenter

import com.amk.stackoverflowreader.mvp.view.ItemView

interface ItemPresenter<V : ItemView> {
    val itemClickListener: ((ItemView) -> Unit)?

    fun getCount(): Int
    fun bindView(view: V)
}