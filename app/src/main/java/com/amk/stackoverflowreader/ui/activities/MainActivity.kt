package com.amk.stackoverflowreader.ui.activities

import android.os.Bundle
import android.view.MenuItem
import com.amk.stackoverflowreader.App
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.presenter.MainPresenter
import com.amk.stackoverflowreader.mvp.view.MainView
import com.amk.stackoverflowreader.navigation.Screens
import com.amk.stackoverflowreader.ui.BackButtonListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

const val TAG = "!@#$ TAG"

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private lateinit var bottomNavView: BottomNavigationView


    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_question -> {
                    router.newRootScreen(Screens.QuestionScreen())
                    setSelectItem(item)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_user -> {
                    router.newRootScreen(Screens.UserListScreen())
                    setSelectItem(item)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_login -> {
                    router.newRootScreen(Screens.LoginScreen())
                    setSelectItem(item)
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
        }

    private fun setSelectItem(item: MenuItem) {
        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)

        for (i in 0 until bottomNavView.menu.size()) {
            item.isChecked = item == bottomNavView.menu.getItem(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
        bottomNavView = findViewById(R.id.nav_view)
        bottomNavView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
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