package com.logcat.almarker

import android.os.Bundle
import com.logcat.almarker.base.BaseActivity
import com.logcat.almarker.util.FragmentHelper

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = TestFragment()
        FragmentHelper.fragmentManager(supportFragmentManager)
                .fragment(fragment)
                .replace(false)
    }
}
