package ac.summer.shopmaniac.view

import ac.summer.shopmaniac.presenter.ShopmaniacPresenter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.KoinComponent
import org.koin.core.inject

class ItemSwipeToggleBehavior : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT), KoinComponent {
    private val presenter: ShopmaniacPresenter by inject()
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        presenter.doneItem(viewHolder.itemId)
    }
}