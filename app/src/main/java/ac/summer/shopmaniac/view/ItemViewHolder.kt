package ac.summer.shopmaniac.view

import ac.summer.shopmaniac.R
import ac.summer.shopmaniac.domain.ItemRowModel
import ac.summer.shopmaniac.presenter.ShopmaniacPresenter
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.KoinComponent
import org.koin.core.inject


class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), KoinComponent {
    private val presenter: ShopmaniacPresenter by inject()
    private val invisibleText: EditText by lazy { view.findViewById<EditText>(R.id.invisible_text) }
    private val itemName: TextView by lazy { view.findViewById<TextView>(R.id.item_name) }

    fun bind(item: ItemRowModel) {
        when (item.type) {
            ItemRowModel.NEW -> onBindNewRow(item)
            ItemRowModel.NORMAL -> onBindNormalRow(item)
        }
    }

    private fun onBindNewRow(item: ItemRowModel) {
        itemName.text = null
        invisibleText.requestFocus()
        val imm =
            itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        invisibleText.setOnEditorActionListener { textView, i, keyEvent ->
            imm.hideSoftInputFromWindow(textView.windowToken, 0)
            textView.clearFocus()
            val text = textView.text
            textView.text = ""
            presenter.setNewItem(text.toString())
            true
        }
    }

    private fun onBindNormalRow(item: ItemRowModel) {
        invisibleText.visibility = View.GONE
        itemName.text = item.text
    }
}