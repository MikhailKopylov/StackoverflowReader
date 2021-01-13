package com.amk.stackoverflowreader.mvp.presenter

import com.amk.stackoverflowreader.mvp.model.User
import com.amk.stackoverflowreader.mvp.model.UserRepository
import com.amk.stackoverflowreader.mvp.presenter.list.UserItemPresenter
import com.amk.stackoverflowreader.mvp.view.UserListView
import com.amk.stackoverflowreader.mvp.view.list.UserItemView
import moxy.MvpPresenter

class UserListPresenter(val view: UserListView) : MvpPresenter<UserListView>() {

    private val userRepository = UserRepository()
    val userItemPresenterImpl = UserItemPresenterImpl()

    inner class UserItemPresenterImpl : UserItemPresenter {
        val listUser = mutableListOf<User>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = listUser.size

        override fun bindView(view: UserItemView) {
            view.setUserLogin(listUser[view.pos].login)
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        userItemPresenterImpl.listUser.addAll(userRepository.getUsers())
        viewState.updateData()
    }


}