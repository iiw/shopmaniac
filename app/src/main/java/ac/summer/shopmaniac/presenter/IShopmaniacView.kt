package ac.summer.shopmaniac.presenter

import ac.summer.shopmaniac.domain.ItemRowModel

interface IShopmaniacView {
    fun setItems(items: List<ItemRowModel>)
    fun setAddButtonVisible(isVisible: Boolean)
    fun clearAllFocus()
    fun hideSoftKeyboard()
}