package com.logcat.almarker.base

import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.logcat.ui_module.action_bar.ActionBar
import com.logcat.ui_module.loading.LoadingViewType

abstract class BaseFragment : Fragment() {
    protected val baseActivity: BaseActivity?
        get() {
            return when (activity) {
                is BaseActivity -> activity as BaseActivity
                else -> null
            }
        }

    /**
     * Allow device back button
     * Public getter
     * Protected setter
     */
    var isBackButtonAllowed: Boolean = getBackButtonAllowed()
        protected set(value) { field = value }

    override fun onResume() {
        super.onResume()

        showActionBar(getActionBarBuilder())
    }

    protected fun showLoading(isShow: Boolean, loadingViewType: LoadingViewType? = null) {
        baseActivity?.showLoading(isShow, loadingViewType)
    }

    protected fun showActionBar(actionBarBuilder: ActionBar.Builder?) {
        getActionBarContainer()?.let { container ->
            actionBarBuilder?.let {
                it.build()
                container.visibility = View.VISIBLE
            } ?: run {
                container.visibility = View.GONE
            }
        }
    }

    /**
     * Action bar setup
     */
    protected abstract fun getActionBarContainer(): ViewGroup?
    protected abstract fun getActionBarBuilder(): ActionBar.Builder?

    open fun getBackButtonAllowed(): Boolean = true
}