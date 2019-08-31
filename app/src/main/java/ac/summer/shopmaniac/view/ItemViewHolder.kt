package ac.summer.shopmaniac.view

import ac.summer.shopmaniac.R
import ac.summer.shopmaniac.domain.ItemRowModel
import ac.summer.shopmaniac.presenter.ShopmaniacPresenter
import android.content.Context
import android.view.View
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
    private val imm by lazy {
        view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
    private lateinit var item: ItemRowModel

    fun bind(item: ItemRowModel) {
        when (item.type) {
            ItemRowModel.NEW -> onBindNewRow()
            ItemRowModel.NORMAL -> onBindNormalRow(item)
        }
    }

    private fun onBindNewRow() {
        itemName.text = null
        invisibleText.setText("")
        invisibleText.requestFocus()
        showSoftKeyboard()
        invisibleText.setOnEditorActionListener { textView, _, _ ->
            hideSoftKeyboard()
            textView.clearFocus()
            val text = textView.text
            textView.text = ""
            presenter.setNewItem(text.toString())
            true
        }
    }

    private fun showSoftKeyboard() {
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun hideSoftKeyboard() {
        imm.hideSoftInputFromWindow(invisibleText.windowToken, 0)
    }

    private fun onBindNormalRow(item: ItemRowModel) {
        this.item = item
        invisibleText.visibility = View.GONE
        itemName.text = item.text
    }
}