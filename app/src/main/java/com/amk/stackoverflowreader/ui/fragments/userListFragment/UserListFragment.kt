package com.amk.stackoverflowreader.ui.fragments.userListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.presenter.listUser.UserListPresenter
import com.amk.stackoverflowreader.mvp.view.UserListView
import com.amk.stackoverflowreader.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_user_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListFragment : MvpAppCompatFragment(), UserListView, BackButtonListener {

    private val presenter by moxyPresenter {
        UserListPresenter(App.instance.router)
    }

    private lateinit var mainPresenter:MainPresenter

    private lateinit var adapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(mainPresenter: MainPresenter) : UserListFragment{
            val fragment = UserListFragment()
            fragment.mainPresenter = mainPresenter
            return fragment
        }

    }

    override fun init() {
        presenter.mainPresenter = mainPresenter

        user_list_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(presenter.listUserItemPresenterImpl)
        user_list_recycler_view.adapter = adapter
    }

    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun showClick(pos:Int) {
        Toast.makeText(context, "Press $pos", Toast.LENGTH_SHORT).show()
    }

    override fun pressedBackButton() = presenter.pressedBackButton()
}