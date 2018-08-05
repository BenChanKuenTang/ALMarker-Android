package com.logcat.ui_module.action_bar

import android.content.Context
import android.view.View
import android.view.ViewGroup

class ActionBar {
    companion object Builder {
        private var container: ViewGroup? = null
        private var actionBarView: View? = null

        fun container(container: ViewGroup?): Builder {
            this.container = container
            return this
        }

        fun <MODEL: ActionBarModel> actionBar(context: Context?, actionBar: IActionBar<MODEL>, actionBarModel: MODEL): Builder {
            context?.let {
                val safeContainer = container
                if (safeContainer != null) {
                    actionBarView = actionBar.getActionBar(it, safeContainer)
                    actionBar.setModel(actionBarModel)
                }
            }
            return this
        }

        fun build(): Builder {
            container?.addView(actionBarView)
            return this
        }
    }
}