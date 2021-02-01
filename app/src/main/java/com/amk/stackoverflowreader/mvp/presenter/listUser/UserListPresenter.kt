package com.amk.stackoverflowreader.mvp.presenter.listUser

import android.util.Log
import com.amk.stackoverflowreader.mvp.model.entity.User
import com.amk.stackoverflowreader.mvp.model.repository.UserRepository
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.view.ItemView
import com.amk.stackoverflowreader.mvp.view.listUser.UserListView
import com.amk.stackoverflowreader.mvp.view.listUser.UserItemView
import com.amk.stackoverflowreader.ui.activities.TAG
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserListPresenter(private val router: Router) : MvpPresenter<UserListView>() {

    val listUserItemPresenterImpl = ListUserItemPresenterImpl()

    lateinit var mainPresenter: MainPresenter

    inner class ListUserItemPresenterImpl : ListUserItemPresenter {
        val listUser = mutableListOf<User>()

        override val itemClickListener: ((ItemView) -> Unit)
            get() {
                return { mainPresenter.startUserScreen(listUser[it.pos].id) }
            }

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
        UserRepository
            .getUsers()
            .filter { it != null }
            .subscribe({
                listUserItemPresenterImpl.listUser.add(it)
                viewState.updateData()
            }, {
                Log.d(TAG, it.toString())
            }, {
                if (listUserItemPresenterImpl.listUser.isEmpty()) {
                    Log.d(TAG, "Not find user!")
                }
            })
    }

    fun pressedBackButton(): Boolean {
        router.exit()
        return true
    }

}