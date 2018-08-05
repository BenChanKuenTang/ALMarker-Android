package com.logcat.almarker.splash

import android.content.Intent
import com.logcat.almarker.base.BaseActivity
import com.logcat.almarker.signin.SignInActivity

class SplashScreenRouter(private val activity: BaseActivity) {
    fun routeToSignInActivity() {
        activity.startActivity(Intent(activity, SignInActivity::class.java))
    }
}