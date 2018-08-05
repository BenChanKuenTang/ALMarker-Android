package com.logcat.almarker.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.logcat.almarker.R
import com.logcat.ui_module.loading.LoadingViewType
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    protected open fun getLayoutId(): Int = R.layout.activity_main

    fun showLoading(isShow: Boolean, loadingViewType: LoadingViewType? = null) {
        if (isShow && loadingViewType != null) startLoading(loadingViewType) else hideLoading()
    }

    private fun startLoading(loadingViewType: LoadingViewType) {
        loadingIndicator.showLoading(loadingViewType)
    }

    private fun hideLoading() {
        loadingIndicator.hideLoading()
    }

    override fun onBackPressed() {
        if (getBackButtonAllowed()) {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.mainContainer)
            when (currentFragment) {
                is BaseFragment -> {
                    if (currentFragment.isBackButtonAllowed) {
                        super.onBackPressed()
                    }
                }
            }
        }
    }

    /**
     * Allow device back button
     */
    open fun getBackButtonAllowed(): Boolean = true
}