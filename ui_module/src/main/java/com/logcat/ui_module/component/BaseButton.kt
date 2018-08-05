package com.logcat.ui_module.component

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import com.logcat.ui_module.DynamicFontHelper
import com.logcat.ui_module.DynamicFontModel
import com.logcat.ui_module.IDynamicFont
import com.logcat.ui_module.R

enum class ButtonStyle {
    PRIMARY,
    SECONDARY;

    @DrawableRes
    fun getDrawableRes(): Int {
        return when (this) {
            PRIMARY -> {
                R.drawable.drawable_button1
            }
            SECONDARY -> {
                R.drawable.drawable_button2
            }
        }
    }
}

class BaseButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : AppCompatButton(context, attrs), IDynamicFont<BaseButton> {
    override var mDynamicFontModel: DynamicFontModel? = null

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int): this(context, attrs)

    init {
        processAttr(attrs)
        init()
    }

    private fun processAttr(attrs: AttributeSet? = null) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.BaseButton)

            mDynamicFontModel = getFontSizeAttr(typedArray, textSize)

            val buttonStyleValue = typedArray.getInt(R.styleable.BaseButton_button_style, 0)
            val buttonStyle = ButtonStyle.values()[buttonStyleValue]
            setButtonStyle(buttonStyle)

            typedArray.recycle()
        }
    }

    private fun init() {
        setAllCaps(false)
        updateFontSettings(mDynamicFontModel)
    }

    private fun setButtonStyle(buttonStyle: ButtonStyle) {
        background = ContextCompat.getDrawable(context, buttonStyle.getDrawableRes())
    }

    override fun getIsDynamicFontEnabledStyleable(): Int = R.styleable.BaseButton_enable_dynamic_font

    override fun getLargeTextSizeStyleable(): Int = R.styleable.BaseButton_text_size_large

    override fun getFontPathStyleable(): Int = R.styleable.BaseButton_font_path

    override fun updateFontSettings(dynamicFontModel: DynamicFontModel?) {
        DynamicFontHelper.updateFontSettings(this, dynamicFontModel)
    }
}