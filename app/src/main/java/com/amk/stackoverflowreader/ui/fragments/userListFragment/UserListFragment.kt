package com.amk.stackoverflowreader.ui.fragments.userListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.di.user.UserSubcomponent
import com.amk.stackoverflowreader.mvp.model.api.OrderBy
import com.amk.stackoverflowreader.mvp.model.api.SortedForUser
import com.amk.stackoverflowreader.mvp.model.entity.contexImplementation.GlideImageLoader
import com.amk.stackoverflowreader.mvp.presenter.listUser.UserListPresenter
import com.amk.stackoverflowreader.mvp.view.listUser.UserListView
import com.amk.stackoverflowreader.ui.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListFragment : MvpAppCompatFragment(), UserListView, BackButtonListener {

    private lateinit var adapter: UserListAdapter
    private var userSubcomponent: UserSubcomponent? = null
    private var sorted: SortedForUser = SortedForUser.Reputation
    private var order: OrderBy = OrderBy.Desc

    private val presenter by moxyPresenter {
        userSubcomponent = App.instance.initUserSubcomponent()
        UserListPresenter(AndroidSchedulers.mainThread()).apply {
            userSubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserListFragment()
    }


    override fun init() {
        user_list_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(presenter.userItemPresenter, GlideImageLoader())
        user_list_recycler_view.adapter = adapter

        search_question.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.loadFilterUsers(search_question.query.toString(), sorted, order)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        chooseSortedBy()
        chooseOrderBy()
    }

    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun showClick(pos: Int) {
        Toast.makeText(context, "Press $pos", Toast.LENGTH_SHORT).show()
    }

    override fun hideLoadUsers() {
        tv_load_users.visibility = View.GONE
    }

    override fun release() {
        userSubcomponent = null
        App.instance.releaseUserSubcomponent()
    }

    override fun pressedBackButton() = presenter.pressedBackButton()

    private fun chooseSortedBy() {
        val adapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                SortedForUser.values()
            )
        }
        adapter?.let {
            it.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinner_sorted.adapter = adapter
            spinner_sorted.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    sorted = SortedForUser.values()[position]
//                    if (search_user.query.isEmpty()) {
                    presenter.loadAllUsers(sorted, order)
//                    } else {
//                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    sorted = SortedForUser.Reputation
                }
            }
        }
    }

    private fun chooseOrderBy() {
        val adapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                OrderBy.values()
            )
        }
        adapter?.let {
            it.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinner_order_user.adapter = adapter
            spinner_order_user.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        order = OrderBy.values()[position]
                        presenter.loadAllUsers(sorted, order)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        order = OrderBy.Desc
                    }
                }
        }
    }
}