package com.amk.stackoverflowreader.ui.fragments.userFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.di.user.UserSubcomponent
import com.amk.stackoverflowreader.mvp.model.entity.user.User
import com.amk.stackoverflowreader.mvp.presenter.user.UserPresenter
import com.amk.stackoverflowreader.mvp.view.user.UserView
import com.amk.stackoverflowreader.ui.BackButtonListener
import com.amk.stackoverflowreader.ui.MyWebViewClient
import kotlinx.android.synthetic.main.fragment_answer.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var userSubcomponent: UserSubcomponent? = null

    private val presenter by moxyPresenter {
        userSubcomponent = App.instance.initUserSubcomponent()
        val user = arguments?.getParcelable<User>(USER_ARG) as User
        UserPresenter(user).apply {
            userSubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_answer, container, false)
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

    @SuppressLint("SetJavaScriptEnabled")
    override fun showUser(link: String) {
        with(wv_question) {
            settings.javaScriptEnabled = true
            webViewClient = MyWebViewClient()
            loadUrl(link)
        }
    }

    override fun release() {
        userSubcomponent = null
        App.instance.releaseAnswerSubcomponent()
    }

    override fun pressedBackButton() = presenter.pressedBackButton()
}