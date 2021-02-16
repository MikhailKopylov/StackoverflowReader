package com.amk.stackoverflowreader.ui.fragments.questionListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.di.question.QuestionSubcomponent
import com.amk.stackoverflowreader.mvp.model.api.OrderBy
import com.amk.stackoverflowreader.mvp.model.api.SortedForQuestion
import com.amk.stackoverflowreader.mvp.model.entity.contexImplementation.GlideImageLoader
import com.amk.stackoverflowreader.mvp.presenter.listQuestion.QuestionListPresenter
import com.amk.stackoverflowreader.mvp.view.listQuestion.QuestionListView
import com.amk.stackoverflowreader.ui.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_question_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class QuestionListFragment : MvpAppCompatFragment(), QuestionListView, BackButtonListener {

    private var questionSubcomponent: QuestionSubcomponent? = null
    private var sorted: SortedForQuestion = SortedForQuestion.Activity
    private var order: OrderBy = OrderBy.Desc

    private val presenter by moxyPresenter {
        questionSubcomponent = App.instance.initQuestionSubcomponent()
        QuestionListPresenter(AndroidSchedulers.mainThread()).apply {
            questionSubcomponent?.inject(this)
        }
    }

    private lateinit var adapter: QuestionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_question_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuestionListFragment()
    }

    override fun init() {
        question_list_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = QuestionListAdapter(presenter.questionItemPresenterImpl, GlideImageLoader())
        question_list_recycler_view.adapter = adapter

        search_question.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchQuestion(search_question.query.toString(), sorted, order)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        //Выбор сортировки
        chooseSortedBy()

        chooseOrderBy()
    }


    override fun updateData() {
        adapter.notifyDataSetChanged()
    }

    override fun showClick(pos: Int) {

        Toast.makeText(context, "Press $pos", Toast.LENGTH_SHORT).show()
    }

    override fun hideLoadQuestions() {
        tv_load_data.visibility = View.GONE
    }

    override fun release() {
        questionSubcomponent = null
        App.instance.releaseQuestionSubcomponent()
    }

    override fun pressedBackButton() = presenter.pressedBackButton()

    private fun chooseSortedBy() {
        val adapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                SortedForQuestion.values()
            )
        }
        adapter?.let {
            it.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinner_sorted.adapter = adapter
            spinner_sorted.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    sorted = SortedForQuestion.values()[position]
                    if (search_question.query.isEmpty()) {
                        presenter.searchQuestion(sorted, order)
                    } else {
                        presenter.searchQuestion(search_question.query.toString(), sorted, order)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    sorted = SortedForQuestion.Activity
                }
            }
        }
    }

    private fun chooseOrderBy() {
        val adapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                OrderBy.values()
            )
        }
        adapter?.let {
            it.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinner_order.adapter = adapter
            spinner_order.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    order = OrderBy.values()[position]
                    if (search_question.query.isEmpty()) {
                        presenter.searchQuestion(sorted, order)
                    } else {
                        presenter.searchQuestion(search_question.query.toString(), sorted, order)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    order = OrderBy.Desc
                }
            }
        }
    }
}
