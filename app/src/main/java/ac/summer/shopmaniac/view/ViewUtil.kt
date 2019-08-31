package ac.summer.shopmaniac.view

import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

object ViewUtil {
    fun showSoftKeyboard(context: Context) {
        getInputMethodManager(context).toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    fun hideSoftKeyboard(context: Context, windowToken: IBinder) {
        getInputMethodManager(context).hideSoftInputFromWindow(windowToken, 0)
    }

    private fun getInputMethodManager(context: Context): InputMethodManager {
        return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
}