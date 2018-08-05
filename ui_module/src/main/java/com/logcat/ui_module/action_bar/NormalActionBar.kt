package com.logcat.ui_module.action_bar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.logcat.ui_module.R
import com.logcat.ui_module.component.BaseTextView

data class NormalActionBarModel(
        val btnLeftResId: Int,
        val title: String? = null
) : ActionBarModel()

class NormalActionBar : IActionBar<NormalActionBarModel> {
    private lateinit var view: View

    override fun getActionBar(context: Context, container: ViewGroup): View {
        view = LayoutInflater.from(context).inflate(R.layout.view_action_bar_normal, container, false)
        return view
    }

    override fun setModel(model: NormalActionBarModel) {
        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val tvTitle = view.findViewById<BaseTextView>(R.id.tvTitle)

        btnBack.setImageResource(model.btnLeftResId)
        model.title?.let {
            tvTitle.text = it
            tvTitle.visibility = View.VISIBLE
        } ?: run {
            tvTitle.visibility = View.GONE
        }
    }
}