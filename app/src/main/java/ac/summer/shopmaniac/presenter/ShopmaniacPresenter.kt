package ac.summer.shopmaniac.presenter

import ac.summer.shopmaniac.domain.ItemRowModel
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class ShopmaniacPresenter {
    private var view: IShopmaniacView? = null
    private var items: ArrayList<ItemRowModel> = arrayListOf()
    private var nextId: Int = 1
    fun attachView(activity: IShopmaniacView) {
        view = activity
    }

    fun detachView(activity: IShopmaniacView) {
        if (view == activity)
            view = null
    }

    fun onViewReady() {
        view?.setItems(items)
    }

    fun onKeyboardVisibilityChanged(isOpen: Boolean) {
        if (!isOpen) {
            view?.clearAllFocus()
            setNewItem(null)
        }
    }

    fun createNewItem() {
        view?.setAddButtonVisible(false)
        val oldItems = ArrayList(items)
        items.clear()
        items.add(ItemRowModel(type = ItemRowModel.NEW, id = nextId()))
        items.addAll(oldItems.filter { it.type != ItemRowModel.NEW })
        view?.setItems(items)
    }

    fun setNewItem(title: String?) {
        val oldItems = ArrayList(items)
        items.clear()
        if (title != null) {
            items.add(
                ItemRowModel(
                    id = nextId(),
                    created = (Date().time / 1000).toInt(),
                    isChecked = false,
                    text = title,
                    type = ItemRowModel.NORMAL
                )
            )
        }
        items.addAll(oldItems.filter { it.type != ItemRowModel.NEW })
        view?.setItems(items)
        view?.setAddButtonVisible(true)
    }

    fun removeItem(itemId: Long) {
        val oldItems = ArrayList(items)
        items.clear()
        items.addAll(oldItems.filter { it.id?.toLong() != itemId })
        view?.setItems(items)
        view?.hideSoftKeyboard()
    }

    /**
     * DEBUG
     */
    private fun nextId(): Int {
        Log.w("P", "TODO: REMOVE DEBUG METHOD")
        nextId++
        return nextId
    }
}