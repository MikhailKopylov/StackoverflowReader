package com.amk.stackoverflowreader.mvp.presenter.listQuestion

import android.util.Log
import com.amk.stackoverflowreader.mvp.model.api.ApiHolder
import com.amk.stackoverflowreader.mvp.model.entity.question.Question
import com.amk.stackoverflowreader.mvp.model.repository.QuestionRepository
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IQuestionRepo
import com.amk.stackoverflowreader.mvp.view.ItemView
import com.amk.stackoverflowreader.mvp.view.listQuestion.QuestionItemView
import com.amk.stackoverflowreader.mvp.view.listQuestion.QuestionListView
import com.amk.stackoverflowreader.navigation.Screens
import com.amk.stackoverflowreader.ui.activities.TAG
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class QuestionListPresenter(
    private val mainThreadScheduler: Scheduler,
) : MvpPresenter<QuestionListView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var questionRepository:IQuestionRepo

    val questionItemPresenterImpl = QuestionItemPresenterImpl()


    inner class QuestionItemPresenterImpl : QuestionItemPresenter {
        val listQuestion = mutableListOf<Question>()
        override var itemClickListener: ((ItemView) -> Unit)? = null

        override fun getCount() = listQuestion.size

        override fun bindView(view: QuestionItemView) {
            view.setQuestionBody(listQuestion[view.pos].title)
            view.loadAvatar(listQuestion[view.pos].owner.profileImage)
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        searchQuestion()

        questionItemPresenterImpl.itemClickListener = { itemView ->
            val question = questionItemPresenterImpl.listQuestion[itemView.pos]
            router.navigateTo(Screens.ListAnswerScreen(question))
        }
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