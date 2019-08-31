package ac.summer.shopmaniac.view

import ac.summer.shopmaniac.R
import ac.summer.shopmaniac.domain.ItemRowModel
import ac.summer.shopmaniac.presenter.ShopmaniacPresenter
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.KoinComponent
import org.koin.core.inject


class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), KoinComponent {
    private val presenter: ShopmaniacPresenter by inject()
    private val invisibleText: EditText by lazy { view.findViewById<EditText>(R.id.invisible_text) }
    private val itemName: TextView by lazy { view.findViewById<TextView>(R.id.item_name) }

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
        invisibleText.visibility = View.VISIBLE
        invisibleText.requestFocus()
        ViewUtil.showSoftKeyboard(itemView.context)
        invisibleText.setOnEditorActionListener { textView, _, _ ->
            ViewUtil.hideSoftKeyboard(textView.context, textView.windowToken)
            textView.clearFocus()
            val text = textView.text
            presenter.setNewItem(text.toString())
            true
        }
    }

    private fun onBindNormalRow(item: ItemRowModel) {
        this.item = item
        invisibleText.visibility = View.GONE
        itemName.text = item.text
    }
}