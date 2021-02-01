package com.amk.stackoverflowreader.mvp.view.listUser

import com.amk.stackoverflowreader.mvp.view.ItemView


interface UserItemView : ItemView {
    fun setUserLogin(login: String)
}