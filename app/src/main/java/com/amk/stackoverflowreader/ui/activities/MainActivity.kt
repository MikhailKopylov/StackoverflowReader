package com.amk.stackoverflowreader.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.ui.fragments.userListFragment.UserListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, UserListFragment.newInstance())
            .commit()
    }
}