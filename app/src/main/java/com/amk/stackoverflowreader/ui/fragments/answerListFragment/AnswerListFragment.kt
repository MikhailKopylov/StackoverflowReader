package com.amk.stackoverflowreader.ui.fragments.answerListFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.model.entity.question.Question
import com.amk.stackoverflowreader.mvp.presenter.listAnswer.AnswerListPresenter
import com.amk.stackoverflowreader.mvp.view.listAnswer.AnswerListView
import com.amk.stackoverflowreader.ui.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_answer_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AnswerListFragment : MvpAppCompatFragment(), AnswerListView, BackButtonListener {

    private val presenter by moxyPresenter {
        val question = arguments?.getParcelable<Question>(ANSWER_ARG) as Question
        AnswerListPresenter(question, AndroidSchedulers.mainThread(), App.instance.router)
    }

    private lateinit var adapter: AnswerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_answer_list, container, false)
    }

    companion object {
        private const val ANSWER_ARG = "answerArg"

        @JvmStatic
        fun newInstance(question: Question) = AnswerListFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ANSWER_ARG, question)
            }
        }

    }

    override fun init() {

        answer_list_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = AnswerListAdapter(presenter.answerItemPresenterImpl)
        answer_list_recycler_view.adapter = adapter

    }

    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun showClick(pos: Int) {
        Toast.makeText(context, "Press $pos", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun setQuotaRemaining(quotaRemaining: Int) {
        tv_quota_remaining.text = "Quota remaining =  $quotaRemaining"
    }

    @SuppressLint("SetTextI18n")
    override fun setHasMore(hasMore: Boolean) {
        tv_has_more.text = "Has more: $hasMore"
    }

    override fun pressedBackButton() = presenter.pressedBackButton()
}