package com.amk.stackoverflowreader.mvp.presenter.listUser

import com.amk.stackoverflowreader.mvp.model.User
import com.amk.stackoverflowreader.mvp.model.UserRepository
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.view.UserListView
import com.amk.stackoverflowreader.mvp.view.listUser.UserItemView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserListPresenter(private val router: Router) : MvpPresenter<UserListView>() {

    val listUserItemPresenterImpl = ListUserItemPresenterImpl()

    private val userRepository = UserRepository()
    lateinit var mainPresenter: MainPresenter

    inner class ListUserItemPresenterImpl : ListUserItemPresenter {
        val listUser = mutableListOf<User>()

        override val itemClickListener: ((UserItemView) -> Unit)
            get() {
                return { mainPresenter.startUserScreen(listUser[it.pos]) }
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
        listUserItemPresenterImpl.listUser.addAll(userRepository.getUsers())
        viewState.updateData()
    }

    fun pressedBackButton(): Boolean {
        router.exit()
        return true
    }

}