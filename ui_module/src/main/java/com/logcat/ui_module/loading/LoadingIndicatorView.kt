package com.logcat.ui_module.loading

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.logcat.ui_module.R

enum class LoadingViewType {
    PROGRESS_BAR;

    fun getLayoutRes(): Int {
        return when (this) {
            PROGRESS_BAR -> R.layout.view_loading_progress_bar
        }
    }
}

class LoadingIndicatorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.view_loading_indicator, this, true)
        gravity = Gravity.CENTER
    }

    fun showLoading(loadingViewType: LoadingViewType) {
        removeAllViews()
        addLoadingView(loadingViewType)
        visibility = View.VISIBLE
    }

    fun hideLoading() {
        visibility = View.GONE
    }

    private fun addLoadingView(loadingViewType: LoadingViewType) {
        val viewId = loadingViewType.getLayoutRes()
        LayoutInflater.from(context).inflate(viewId, this, true)

        when (loadingViewType) {
            LoadingViewType.PROGRESS_BAR -> initProgressBar()
        }
    }

    private fun initProgressBar() {
        val view = findViewById<ProgressBar>(R.id.vProgressBar)
        view?.let {

        }
    }
}