package com.amk.stackoverflowreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amk.stackoverflowreader.view.UserAccountFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, UserAccountFragment.newInstance())
            .commit()
    }
}