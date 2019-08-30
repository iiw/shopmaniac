package ac.summer.shopmaniac.presenter

import ac.summer.shopmaniac.view.ShopmaniacActivity
import android.widget.Toast

class ShopmaniacPresenter {
    private var view: ShopmaniacActivity? = null
    fun attachView(activity: ShopmaniacActivity) {
        view = activity
    }

    fun detachView(activity: ShopmaniacActivity) {
        view = null
    }

    fun onViewReady() {
        Toast.makeText(view?.applicationContext, "View ready", Toast.LENGTH_LONG).show()
        view?.setItems(listOf())
    }
}