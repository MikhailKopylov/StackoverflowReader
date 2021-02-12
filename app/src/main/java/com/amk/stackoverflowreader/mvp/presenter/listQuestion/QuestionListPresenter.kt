package com.amk.stackoverflowreader.mvp.presenter.listQuestion

import android.util.Log
import com.amk.stackoverflowreader.mvp.model.api.SortedBy
import com.amk.stackoverflowreader.mvp.model.entity.question.Question
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
    lateinit var questionRepository: IQuestionRepo

    val questionItemPresenterImpl = QuestionItemPresenterImpl()


    inner class QuestionItemPresenterImpl : QuestionItemPresenter {
        val listQuestion = mutableListOf<Question>()
        override var itemClickListener: ((ItemView) -> Unit)? = null

        override fun getCount() = listQuestion.size

        override fun bindView(view: QuestionItemView) {
            with(listQuestion[view.pos]) {
                view.setQuestionBody(title)
                owner.profileImage?.let { view.loadAvatar(it) }
                view.setAnswersCount(answerCount)
                view.setViewsCount(viewCount)
                view.setVotesCount(votes = score)
            }
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        searchQuestion(SortedBy.Activity)

        questionItemPresenterImpl.itemClickListener = { itemView ->
            val question = questionItemPresenterImpl.listQuestion[itemView.pos]
            router.navigateTo(Screens.ListAnswerScreen(question))
        }
//        loadData()
    }

    fun searchQuestion(question: String, sortBy: SortedBy) {
        questionRepository.getFindQuestions(question, sortBy)
            .observeOn(mainThreadScheduler)
            .subscribe({
                questionItemPresenterImpl.listQuestion.clear()
                questionItemPresenterImpl.listQuestion.addAll(it.items)
                viewState.updateData()
            }, {

            })
    }


    fun searchQuestion(sortBy: SortedBy) {
        questionRepository.getQuestions(sortBy)
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

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}