package ac.summer.shopmaniac.presenter

import ac.summer.shopmaniac.domain.ItemRowModel

class ShopmaniacPresenter {
    private var view: IShopmaniacView? = null
    private var items: ArrayList<ItemRowModel> = arrayListOf()
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

    fun newItem() {
        items.add(ItemRowModel(type = ItemRowModel.NEW))
        view?.setItems(items)
        view?.showKeyboardNewItem()
    }
}