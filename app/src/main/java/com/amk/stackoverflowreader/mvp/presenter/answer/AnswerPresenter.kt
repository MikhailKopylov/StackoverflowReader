package com.amk.stackoverflowreader.mvp.presenter.answer

import android.util.Log
import com.amk.stackoverflowreader.mvp.model.api.ApiHolder
import com.amk.stackoverflowreader.mvp.model.entity.question.Question
import com.amk.stackoverflowreader.mvp.model.repository.AnswerRepository
import com.amk.stackoverflowreader.mvp.view.answer.AnswerView
import com.amk.stackoverflowreader.ui.activities.TAG
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class AnswerPresenter(
    private val question: Question,
    private val mainThreadScheduler: Scheduler,
    private val router: Router
) : MvpPresenter<AnswerView>() {

    private val answerRepository = AnswerRepository(ApiHolder().api)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        answers()
    }

    private fun answers() {
        answerRepository.getAnswers(question.questionId)
            .observeOn(mainThreadScheduler)
            .subscribe({
                viewState.showQuestionAndAnswers(it.items[0].questionId)
            }, {
                Log.e(TAG, it.message.toString())
            })
    }

    fun pressedBackButton(): Boolean {
        router.exit()
        return true
    }

}