package com.amk.stackoverflowreader.navigation

import androidx.fragment.app.Fragment
import com.amk.stackoverflowreader.ui.fragments.userListFragment.UserListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen():SupportAppScreen(){
        override fun getFragment(): Fragment? = UserListFragment.newInstance()
    }
}