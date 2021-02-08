package com.amk.stackoverflowreader.mvp.presenter.listAnswer

import android.util.Log
import com.amk.stackoverflowreader.mvp.model.api.ApiHolder
import com.amk.stackoverflowreader.mvp.model.entity.answer.Answer
import com.amk.stackoverflowreader.mvp.model.entity.question.Question
import com.amk.stackoverflowreader.mvp.model.repository.AnswerRepository
import com.amk.stackoverflowreader.mvp.view.ItemView
import com.amk.stackoverflowreader.mvp.view.listAnswer.AnswerItemView
import com.amk.stackoverflowreader.mvp.view.listAnswer.AnswerListView
import com.amk.stackoverflowreader.ui.activities.TAG
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class AnswerListPresenter(
    private val question: Question,
    private val mainThreadScheduler: Scheduler,
    private val router: Router
) : MvpPresenter<AnswerListView>() {

    private val answerRepository = AnswerRepository(ApiHolder().api)
    val answerItemPresenterImpl = AnswerItemPresenterImpl()


    inner class AnswerItemPresenterImpl : IAnswerItemPresenter {
        val listAnswer = mutableListOf<Answer>()
        override val itemClickListener: ((ItemView) -> Unit)
            get() {
                return { viewState.showClick(it.pos) }
            }

        override fun getCount() = listAnswer.size

        override fun bindView(view: AnswerItemView) {
            with(listAnswer[view.pos]) {
                view.setOwnerName(owner.displayName)
                view.setContentLicense(contentLicense)
                view.setAnswerId(answerId)
                view.setIsAccepted(isAccepted)
                view.setScore(score)
                view.setQuestionId(questionId)
                view.loadAvatar(owner.profileImage)
            }
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        answers()
    }

    private fun answers() {
        answerRepository.getAnswers(question.questionId)
            .observeOn(mainThreadScheduler)
            .subscribe({
                answerItemPresenterImpl.listAnswer.clear()
                //TODO this is a temporary solution until the issue of displaying the question body is resolved!!!
                if (it.items.isNotEmpty()) {
                    answerItemPresenterImpl.listAnswer.add(it.items[0])
                }
                viewState.setHasMore(it.hasMore)
                viewState.setQuotaRemaining(it.quotaRemaining)
                viewState.showQuestion(it.items[0].questionId)
                viewState.updateData()
            }, {
                Log.e(TAG, it.message.toString())
            })
    }

    fun pressedBackButton(): Boolean {
        router.exit()
        return true
    }

}