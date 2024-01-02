package com.buyehou.richtext

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

/**
 * @author Rosen
 */
class RichText : AppCompatEditText {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {}

}