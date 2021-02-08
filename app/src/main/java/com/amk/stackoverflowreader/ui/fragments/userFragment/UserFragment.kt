package com.amk.stackoverflowreader.ui.fragments.userFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.model.entity.User
import com.amk.stackoverflowreader.mvp.presenter.user.UserPresenter
import com.amk.stackoverflowreader.mvp.view.user.UserView
import com.amk.stackoverflowreader.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<User>(USER_ARG) as User
        UserPresenter(user, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    companion object {
        private const val USER_ARG = "userArg"

        @JvmStatic
        fun newInstance(user: User) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    override fun setLoginUser(login: String) {
        login_text_view.text = login
    }

    override fun pressedBackButton() = presenter.pressedBackButton()

}