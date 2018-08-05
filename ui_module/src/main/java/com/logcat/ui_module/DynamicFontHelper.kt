package com.logcat.ui_module

import android.graphics.Typeface
import android.util.TypedValue
import android.widget.TextView

object DynamicFontHelper {
    var dynamicFontType: DynamicFontType = DynamicFontType.NORMAL

    fun <VIEW : TextView> updateFontSettings(view: VIEW, dynamicFontModel: DynamicFontModel? = null) {
        if (dynamicFontModel == null) return

        val textSize = if (dynamicFontType == DynamicFontType.LARGE && dynamicFontModel.isDynamicFontEnabled) {
            dynamicFontModel.textSizeLarge
        } else {
            view.textSize
        }

        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

        try {
            val typeFace = Typeface.createFromAsset(view.context.assets, dynamicFontModel.fontPath)
            view.typeface = typeFace
        } catch (e: Exception) {

        }
    }
}