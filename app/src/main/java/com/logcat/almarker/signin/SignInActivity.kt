package com.logcat.almarker.signin

import android.os.Bundle
import com.logcat.almarker.base.BaseActivity
import com.logcat.almarker.util.FragmentHelper

class SignInActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = SignInFragment()
        FragmentHelper.fragmentManager(supportFragmentManager)
                .fragment(fragment)
                .replace(false)
    }
}
