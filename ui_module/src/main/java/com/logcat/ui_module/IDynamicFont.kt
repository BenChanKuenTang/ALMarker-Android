package com.logcat.ui_module

import android.content.res.TypedArray
import android.widget.TextView

data class DynamicFontModel(
        var isDynamicFontEnabled: Boolean = true,
        var textSizeLarge: Float,
        var fontPath: String? = null
)

interface IDynamicFont<VIEW: TextView> {
    var mDynamicFontModel: DynamicFontModel?

    fun getIsDynamicFontEnabledStyleable(): Int
    fun getLargeTextSizeStyleable(): Int
    fun getFontPathStyleable(): Int

    fun getFontSizeAttr(typedArray: TypedArray, defaultTextSize: Float): DynamicFontModel {
        val isDynamicFontEnabled = typedArray.getBoolean(getIsDynamicFontEnabledStyleable(), true)
        val textSizeLarge = typedArray.getDimension(getLargeTextSizeStyleable(), defaultTextSize)
        val fontPath = typedArray.getString(getFontPathStyleable())

        return DynamicFontModel(isDynamicFontEnabled, textSizeLarge, fontPath)
    }

    /**
     * Call after getFontSizeAttr
     * @see getFontSizeAttr
     */
    fun updateFontSettings(dynamicFontModel: DynamicFontModel? = null)
}