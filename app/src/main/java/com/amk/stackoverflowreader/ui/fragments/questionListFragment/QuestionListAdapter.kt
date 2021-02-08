package com.amk.stackoverflowreader.ui.fragments.questionListFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.model.entity.image.IImageLoader
import com.amk.stackoverflowreader.mvp.presenter.listQuestion.QuestionItemPresenter
import com.amk.stackoverflowreader.mvp.view.listQuestion.QuestionItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionListAdapter(
    private val listQuestionItemPresenter: QuestionItemPresenter,
    val imageLoader: IImageLoader<ImageView>
) :
    RecyclerView.Adapter<QuestionListAdapter.QuestionListHolder>() {


    inner class QuestionListHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer, QuestionItemView {

        override var pos = -1

        override fun setQuestionBody(body: String) = with(containerView) {
            tv_question_body.text = body
        }

        override fun loadAvatar(url: String) = with(containerView) {
            imageLoader.loadInto(url, iv_avatar_owner_question)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionListHolder =
        QuestionListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )


    override fun onBindViewHolder(holder: QuestionListHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            listQuestionItemPresenter.itemClickListener?.invoke(
                holder
            )
        }
        listQuestionItemPresenter.bindView(holder)
    }

    override fun getItemCount(): Int = listQuestionItemPresenter.getCount()
}