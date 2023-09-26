package com.buyehou.chronicle.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

/**
 * 富文本编辑器
 * @author Rosen
 * @date 2023/9/26 17:46
 */
@SuppressLint("AppCompatCustomView")
class RichEditText : EditText {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {

    }

}