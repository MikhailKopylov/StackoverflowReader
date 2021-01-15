package com.amk.stackoverflowreader.mvp.presenter.listUser

import com.amk.stackoverflowreader.mvp.view.listUser.ItemView
import com.amk.stackoverflowreader.mvp.view.listUser.UserItemView

interface ItemPresenter<V : ItemView> {
    val itemClickListener: ((UserItemView) -> Unit)

    fun getCount(): Int
    fun bindView(view: V)
}