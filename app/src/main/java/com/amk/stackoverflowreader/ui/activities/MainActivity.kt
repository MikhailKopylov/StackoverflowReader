package com.amk.stackoverflowreader.ui.activities

import android.os.Bundle
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.view.MainView
import com.amk.stackoverflowreader.ui.fragments.userListFragment.UserListFragment
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, UserListFragment.newInstance())
            .commit()
    }
}