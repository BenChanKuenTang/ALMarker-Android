package com.logcat.almarker

import android.os.Bundle
import com.logcat.almarker.base.BaseActivity
import com.logcat.almarker.util.FragmentUtil

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentUtil.replace(supportFragmentManager, TestFragment(), R.id.mainContainer)
    }
}
