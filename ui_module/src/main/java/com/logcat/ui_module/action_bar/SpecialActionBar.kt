package com.logcat.ui_module.action_bar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.logcat.ui_module.R

data class SpecialActionBarModel(
        val btnLeftResId: Int,
        val ivTitle: Int? = null
) : ActionBarModel()

class SpecialActionBar : IActionBar<SpecialActionBarModel> {
    private lateinit var view: View

    override fun getActionBar(context: Context, container: ViewGroup): View {
        view = LayoutInflater.from(context).inflate(R.layout.view_action_bar_special, container, false)
        return view
    }

    override fun setModel(model: SpecialActionBarModel) {
        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val ivTitle = view.findViewById<ImageView>(R.id.ivTitle)

        btnBack.setImageResource(model.btnLeftResId)
        model.ivTitle?.let {
            ivTitle.setImageResource(model.ivTitle)
            ivTitle.visibility = View.VISIBLE
        } ?: run {
            ivTitle.visibility = View.GONE
        }
    }
}