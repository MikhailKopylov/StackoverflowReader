package com.amk.stackoverflowreader.ui.fragments.answerListFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.presenter.listAnswer.IAnswerItemPresenter
import com.amk.stackoverflowreader.mvp.view.listAnswer.AnswerItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_answer.view.*

class AnswerListAdapter(private val answerItemPresenter: IAnswerItemPresenter) :
    RecyclerView.Adapter<AnswerListAdapter.AnswerListHolder>() {


    inner class AnswerListHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer, AnswerItemView {

        @SuppressLint("SetTextI18n")
        override fun setIsAccepted(isAccepted: Boolean) = with(containerView) {
            tv_accepted.text = "isAccepted $isAccepted"
        }

        @SuppressLint("SetTextI18n")
        override fun setScore(score: Int) = with(containerView) {
            tv_score.text = "score $score"
        }

        @SuppressLint("SetTextI18n")
        override fun setAnswerId(answerId: Long) = with(containerView) {
            tv_answer_id.text = "answerId $answerId"
        }

        @SuppressLint("SetTextI18n")
        override fun setQuestionId(questionId: Long) = with(containerView) {
            tv_question_id.text = "questionId $questionId"
        }

        @SuppressLint("SetTextI18n")
        override fun setContentLicense(contentLicense: String) = with(containerView) {
            tv_content_license.text = "contentLicense $contentLicense"
        }

        @SuppressLint("SetTextI18n")
        override fun setOwnerName(displayName: String) = with(containerView) {
            tv_owner_name.text = "displayName $displayName"
        }

        override var pos = -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerListHolder =
        AnswerListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        )


    override fun onBindViewHolder(holder: AnswerListHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            answerItemPresenter.itemClickListener?.invoke(holder)
        }
        answerItemPresenter.bindView(holder)
    }

    override fun getItemCount(): Int = answerItemPresenter.getCount()
}