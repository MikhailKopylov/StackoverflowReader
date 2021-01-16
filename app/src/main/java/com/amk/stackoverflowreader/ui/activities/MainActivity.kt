package com.amk.stackoverflowreader.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.view.MainView
import com.amk.stackoverflowreader.ui.BackButtonListener
import io.reactivex.rxjava3.core.Observable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

const val TAG = "!@#$ TAG"

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigatorHolder = App.instance.navigationHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router)
    }

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Тестирование flatMap
//        testFlatMap()


    }

    private fun testFlatMap() {
        val list = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        val observable: Observable<String> = Observable.create { emitter ->
            try {
                for (i in 0..10) {
                    emitter.onNext("onNext pos - $i = ${list[i]} ")
                    Thread.sleep(100)
                    if (i == 5) {
                        list[1] = -1000 //Пока не закончился поток, изменения отображаются у подписчика
                        list[5] = 2000
                        emitter.onNext("onNext pos - 1 = ${list[1]} ")
                        emitter.onNext("onNext pos - 5 = ${list[5]} ")
                    }
                }
                emitter.onComplete()
                list[2] = -5000
                emitter.onNext("onNext pos - 2 = ${list[2]} ")
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

        observable
            .flatMap { Observable.just(it) }
            .subscribe({
                Log.d(TAG, " $it")
            }, {
                Log.d(TAG, " error $it")
            })
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.let {
            if (it is BackButtonListener && it.pressedBackButton()) {
                return
            }
        }
        presenter.pressedBackButton()
    }
}