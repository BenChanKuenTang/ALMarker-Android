package com.logcat.ui_module.action_bar

import android.content.Context
import android.view.View
import android.view.ViewGroup

open class ActionBarModel(

)

interface IActionBar<MODEL: ActionBarModel> {
    fun getActionBar(context: Context, container: ViewGroup): View

    fun setModel(model: MODEL)
}