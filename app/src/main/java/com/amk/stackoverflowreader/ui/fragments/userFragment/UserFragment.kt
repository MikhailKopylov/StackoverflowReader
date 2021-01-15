package com.amk.stackoverflowreader.ui.fragments.userFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.presenter.user.UserPresenter
import com.amk.stackoverflowreader.mvp.view.user.UserView
import com.amk.stackoverflowreader.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private val presenter by moxyPresenter {
        UserPresenter(App.instance.router)
    }

    private lateinit var mainPresenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.mainPresenter = mainPresenter
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(mainPresenter: MainPresenter): UserFragment {
            val fragment = UserFragment()
            fragment.mainPresenter = mainPresenter
            return fragment
        }
    }

    override fun setLoginUser(login: String) {
        login_text_view.text = login
    }

    override fun pressedBackButton() = presenter.pressedBackButton()

}