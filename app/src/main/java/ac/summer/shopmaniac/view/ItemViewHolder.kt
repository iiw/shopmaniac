package ac.summer.shopmaniac.view

import ac.summer.shopmaniac.BuildConfig
import ac.summer.shopmaniac.R
import ac.summer.shopmaniac.domain.ItemRowModel
import ac.summer.shopmaniac.presenter.ShopmaniacPresenter
import ac.summer.shopmaniac.util.AndroidUtils
import android.os.Build
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.KoinComponent
import org.koin.core.inject


class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), KoinComponent,
    View.OnClickListener {
    private val presenter: ShopmaniacPresenter by inject()
    private val invisibleText: EditText by lazy { view.findViewById<EditText>(R.id.invisible_text) }
    private val itemName: TextView by lazy { view.findViewById<TextView>(R.id.item_name) }
    val mainLayout: View by lazy { view.findViewById<View>(R.id.item_main_layout) }
    val removeLayout: View by lazy { view.findViewById<View>(R.id.item_remove_layout) }
    val doneLayout: View by lazy { view.findViewById<View>(R.id.item_done_layout) }
    private val normalBackgroundColor: Int by lazy {
        AndroidUtils.getColor(
            view.context,
            R.color.itemBackground
        )
    }
    private val doneBackgroundColor: Int by lazy {
        AndroidUtils.getColor(
            view.context,
            R.color.itemBackgroundDone
        )
    }
    private val textColor: Int by lazy { AndroidUtils.getColor(view.context, R.color.text) }
    private val textColorDone: Int by lazy { AndroidUtils.getColor(view.context, R.color.textDone) }

    private lateinit var item: ItemRowModel

    override fun onClick(p0: View?) {
        presenter.doneItem(itemId)
    }

    fun bind(item: ItemRowModel) {
        when (item.type) {
            ItemRowModel.NEW -> onBindNewRow()
            ItemRowModel.NORMAL -> onBindNormalRow(item)
            ItemRowModel.DONE -> onBindDoneRow(item)
        }
    }

    private fun onBindNewRow() {
        itemView.setOnClickListener(null)
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
        itemView.setOnClickListener(this)
        invisibleText.visibility = View.GONE
        itemName.text = item.text
        itemName.setTextColor(textColor)
        mainLayout.setBackgroundColor(normalBackgroundColor)
    }

    private fun onBindDoneRow(item: ItemRowModel) {
        this.item = item
        itemView.setOnClickListener(null)
        invisibleText.visibility = View.GONE
        itemName.text = item.text
        itemName.setTextColor(textColorDone)
        mainLayout.setBackgroundColor(doneBackgroundColor)
    }
}