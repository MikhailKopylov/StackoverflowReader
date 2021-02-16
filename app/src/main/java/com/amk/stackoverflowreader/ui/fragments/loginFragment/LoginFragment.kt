package com.amk.stackoverflowreader.ui.fragments.loginFragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.di.login.LoginSubcomponent
import com.amk.stackoverflowreader.mvp.presenter.login.LoginPresenter
import com.amk.stackoverflowreader.mvp.view.login.LoginView
import com.amk.stackoverflowreader.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class LoginFragment : MvpAppCompatFragment(), LoginView, BackButtonListener {

    private var loginSubcomponent: LoginSubcomponent? = null

    private val presenter by moxyPresenter {
        loginSubcomponent = App.instance.initLoginSubcomponent()
        LoginPresenter().apply {
            loginSubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        private const val USER_ARG = "loginArg"

        @JvmStatic
        fun newInstance() = LoginFragment()


    }

    override fun accessPermission() {
        with(wv_login) {
            val uri = Uri.parse("https://stackoverflow.com/oauth/dialog")
                .buildUpon()
                .appendQueryParameter("client_id", "19408")
                .appendQueryParameter(
                    "redirect_uri",
                    "https://stackexchange.com/oauth/login_success"
                )
                .appendQueryParameter("response_type", "token")
                .build()

            loadUrl(uri.toString())
        }
    }


    override fun release() {
        loginSubcomponent = null
        App.instance.releaseAnswerSubcomponent()
    }

    override fun pressedBackButton() = presenter.pressedBackButton()
}