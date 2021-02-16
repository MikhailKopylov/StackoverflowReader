package com.amk.stackoverflowreader.mvp.presenter.listUser

import com.amk.stackoverflowreader.mvp.model.api.OrderBy
import com.amk.stackoverflowreader.mvp.model.api.SortedForUser
import com.amk.stackoverflowreader.mvp.model.entity.user.User
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IUserRepo
import com.amk.stackoverflowreader.mvp.view.ItemView
import com.amk.stackoverflowreader.mvp.view.listUser.UserItemView
import com.amk.stackoverflowreader.mvp.view.listUser.UserListView
import com.amk.stackoverflowreader.navigation.Screens
import com.amk.stackoverflowreader.ui.Logger
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserListPresenter(
    private val mainThreadScheduler: Scheduler,
) : MvpPresenter<UserListView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userRepository: IUserRepo

    val userItemPresenter = UserItemPresenterImpl()


    companion object {
        private const val TAG = "UserListPresenter"
    }

    inner class UserItemPresenterImpl : UserItemPresenter {
        val listUser = mutableListOf<User>()
        override var itemClickListener: ((ItemView) -> Unit)? = null

        override fun getCount() = listUser.size

        override fun bindView(view: UserItemView) {
            with(listUser[view.pos]) {
                profileImage?.let { view.loadAvatar(it) }
                view.setLogin(displayName)
                view.setReputation(reputation)
                view.setLocation(location ?: "")
            }
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadAllUsers(SortedForUser.Reputation, OrderBy.Desc)
        userItemPresenter.itemClickListener = { itemView ->
            val user = userItemPresenter.listUser[itemView.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }


    fun pressedBackButton(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }

    fun loadAllUsers(sortForUser: SortedForUser, orderBy: OrderBy) {
        userRepository.getAllUsers(sortForUser, orderBy)
            .observeOn(mainThreadScheduler)
            .subscribe({
                userItemPresenter.listUser.clear()
                userItemPresenter.listUser.addAll(it.items)
                viewState.updateData()
                viewState.hideLoadUsers()
            }, {
                Logger.printError(TAG, "Error: ${it.message}")
            })
    }

    fun loadFilterUsers(inNameUser: String, sortForUser: SortedForUser, orderBy: OrderBy) {
        userRepository.getFilterUsers(inNameUser, sortForUser, orderBy)
            .observeOn(mainThreadScheduler)
            .subscribe({
                userItemPresenter.listUser.clear()
                userItemPresenter.listUser.addAll(it.items)
                viewState.updateData()
                viewState.hideLoadUsers()
            }, {
                Logger.printError(TAG, "Error: ${it.message}")
            })
    }

}