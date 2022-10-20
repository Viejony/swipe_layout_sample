package com.co.widetech.swipelayout

import android.content.Context
import kotlin.math.roundToInt

class Utils {

    fun dp2Px(dp: Int, context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).roundToInt()
    }
}