package com.amk.stackoverflowreader.ui.fragments.questionListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.model.entity.contexImplementation.GlideImageLoader
import com.amk.stackoverflowreader.mvp.presenter.listQuestion.QuestionListPresenter
import com.amk.stackoverflowreader.mvp.view.listQuestion.QuestionListView
import com.amk.stackoverflowreader.ui.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_question_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class QuestionListFragment : MvpAppCompatFragment(), QuestionListView, BackButtonListener {

    private val presenter by moxyPresenter {
        QuestionListPresenter(AndroidSchedulers.mainThread()).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private lateinit var adapter: QuestionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_question_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuestionListFragment()
    }

    override fun init() {
        question_list_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = QuestionListAdapter(presenter.questionItemPresenterImpl, GlideImageLoader())
        question_list_recycler_view.adapter = adapter

        search_question.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchQuestion(search_question.query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun showClick(pos: Int) {

        Toast.makeText(context, "Press $pos", Toast.LENGTH_SHORT).show()
    }

    override fun pressedBackButton() = presenter.pressedBackButton()
}