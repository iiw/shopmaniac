package ac.summer.shopmaniac.util

import ac.summer.shopmaniac.R
import android.content.Context
import android.os.Build

object AndroidUtils {
    fun getColor(context: Context, color: Int): Int {
        return if (Build.VERSION.SDK_INT < 23) {
            context.resources.getColor(color)
        } else {
            context.resources.getColor(color, context.theme)
        }
    }
}