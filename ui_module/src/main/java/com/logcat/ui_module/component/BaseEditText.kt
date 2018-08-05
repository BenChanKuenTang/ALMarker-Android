package com.logcat.ui_module.component

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import com.logcat.ui_module.DynamicFontHelper
import com.logcat.ui_module.DynamicFontModel
import com.logcat.ui_module.IDynamicFont
import com.logcat.ui_module.R

class BaseEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        AppCompatEditText(context, attrs, defStyleAttr), IDynamicFont<BaseEditText> {
    override var mDynamicFontModel: DynamicFontModel? = null

    init {
        processAttr(attrs)
        init()
    }

    private fun processAttr(attrs: AttributeSet? = null) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.BaseEditText)

            mDynamicFontModel = getFontSizeAttr(typedArray, textSize)

            typedArray.recycle()
        }
    }

    private fun init() {
        updateFontSettings(mDynamicFontModel)
    }

    override fun getIsDynamicFontEnabledStyleable(): Int = R.styleable.BaseEditText_enable_dynamic_font

    override fun getLargeTextSizeStyleable(): Int = R.styleable.BaseEditText_text_size_large

    override fun getFontPathStyleable(): Int = R.styleable.BaseEditText_font_path

    override fun updateFontSettings(dynamicFontModel: DynamicFontModel?) {
        DynamicFontHelper.updateFontSettings(this, dynamicFontModel)
    }
}