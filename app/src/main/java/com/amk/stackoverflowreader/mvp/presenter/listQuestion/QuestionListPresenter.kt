package com.amk.stackoverflowreader.mvp.presenter.listQuestion

import android.util.Log
import com.amk.stackoverflowreader.mvp.model.api.ApiHolder
import com.amk.stackoverflowreader.mvp.model.entity.Question
import com.amk.stackoverflowreader.mvp.model.repository.QuestionRepository
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.view.ItemView
import com.amk.stackoverflowreader.mvp.view.question.QuestionItemView
import com.amk.stackoverflowreader.mvp.view.question.QuestionListView
import com.amk.stackoverflowreader.ui.activities.TAG
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class QuestionListPresenter(
    private val mainThreadScheduler: Scheduler,
    private val router: Router
) : MvpPresenter<QuestionListView>() {

    lateinit var mainPresenter: MainPresenter
    private val questionRepository = QuestionRepository(ApiHolder().api)
    val questionItemPresenterImpl = QuestionItemPresenterImpl()


    inner class QuestionItemPresenterImpl:QuestionItemPresenter{
        val listQuestion = mutableListOf<Question>()
        override val itemClickListener: ((ItemView) -> Unit)
            get() {
                return {viewState.showClick(it.pos)}
            }

        override fun getCount() = listQuestion.size

        override fun bindView(view: QuestionItemView) {
           view.setQuestionBody(listQuestion[view.pos].title)
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        searchQuestion()
//        loadData()
    }

    fun searchQuestion(question: String) {
        questionRepository.getFindQuestions(question)
            .observeOn(mainThreadScheduler)
            .subscribe({
                questionItemPresenterImpl.listQuestion.clear()
                questionItemPresenterImpl.listQuestion.addAll(it.items)
                viewState.updateData()
            }, {

            })
    }


    private fun searchQuestion() {
        questionRepository.getQuestions()
            .observeOn(mainThreadScheduler)
            .subscribe({
                questionItemPresenterImpl.listQuestion.clear()
                questionItemPresenterImpl.listQuestion.addAll(it.items)
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