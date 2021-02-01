package com.amk.stackoverflowreader.ui.fragments.questionListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.presenter.listQuestion.QuestionListPresenter
import com.amk.stackoverflowreader.mvp.view.question.QuestionListView
import com.amk.stackoverflowreader.ui.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class QuestionListFragment : MvpAppCompatFragment(), QuestionListView, BackButtonListener {

    private val presenter by moxyPresenter {
        QuestionListPresenter(AndroidSchedulers.mainThread(), App.instance.router)
    }

    private lateinit var mainPresenter:MainPresenter

    private lateinit var adapter: QuestionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_question_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(mainPresenter: MainPresenter) : QuestionListFragment{
            val fragment = QuestionListFragment()
            fragment.mainPresenter = mainPresenter
            return fragment
        }

    }

    override fun init() {
        presenter.mainPresenter = mainPresenter

        user_list_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = QuestionListAdapter(presenter.questionItemPresenterImpl)
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