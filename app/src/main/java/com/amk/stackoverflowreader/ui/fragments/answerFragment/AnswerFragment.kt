package com.amk.stackoverflowreader.ui.fragments.answerFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.model.entity.question.Question
import com.amk.stackoverflowreader.mvp.presenter.answer.AnswerPresenter
import com.amk.stackoverflowreader.mvp.view.answer.AnswerView
import com.amk.stackoverflowreader.ui.BackButtonListener
import com.amk.stackoverflowreader.ui.MyWebViewClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_answer.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class AnswerFragment : MvpAppCompatFragment(), AnswerView, BackButtonListener {

    private val presenter by moxyPresenter {
        val question = arguments?.getParcelable<Question>(ANSWER_ARG) as Question
        AnswerPresenter(question, AndroidSchedulers.mainThread(), App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_answer, container, false)
    }

    companion object {
        private const val ANSWER_ARG = "answerArg"

        @JvmStatic
        fun newInstance(question: Question) = AnswerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ANSWER_ARG, question)
            }
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun showQuestionAndAnswers(questionId: Long) {
        val query = "https://stackoverflow.com/q/$questionId"
        with(wv_question) {
            settings.javaScriptEnabled = true
            webViewClient = MyWebViewClient()
            loadUrl(query)
        }
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