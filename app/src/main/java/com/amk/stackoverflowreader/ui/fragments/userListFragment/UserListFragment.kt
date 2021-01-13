package com.amk.stackoverflowreader.ui.fragments.userListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.presenter.UserListPresenter
import com.amk.stackoverflowreader.mvp.view.UserListView
import kotlinx.android.synthetic.main.fragment_user_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListFragment : MvpAppCompatFragment(), UserListView {

    private val presenter by moxyPresenter {
        UserListPresenter(this)
    }

    private lateinit var adapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserListFragment()
    }

    override fun init() {
        user_list_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(presenter.userItemPresenterImpl)
        user_list_recycler_view.adapter = adapter
    }

    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun showClick(pos:Int) {
        Toast.makeText(context, "Press $pos", Toast.LENGTH_SHORT).show()
    }
}