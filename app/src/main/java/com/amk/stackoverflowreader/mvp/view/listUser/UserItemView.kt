package com.amk.stackoverflowreader.mvp.view.listUser

import com.amk.stackoverflowreader.mvp.view.ItemView

interface UserItemView : ItemView {
    fun setLogin(login: String)
    fun loadAvatar(url: String)
    fun setReputation(reputation: Int)
    fun setLocation(location: String)
}