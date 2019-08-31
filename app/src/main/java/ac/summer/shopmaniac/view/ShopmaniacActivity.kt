package ac.summer.shopmaniac.view

import ac.summer.shopmaniac.R
import ac.summer.shopmaniac.domain.ItemRowModel
import ac.summer.shopmaniac.presenter.IShopmaniacView
import ac.summer.shopmaniac.presenter.ShopmaniacPresenter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_fullscreen.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.koin.android.ext.android.inject


class ShopmaniacActivity : AppCompatActivity(), IShopmaniacView {
    private val presenter: ShopmaniacPresenter by inject()
    private lateinit var itemsRecyclerViewAdapter: ItemsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        itemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(applicationContext)
        items_recycler_view.apply {
            adapter = itemsRecyclerViewAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
        button_add.setOnClickListener {
            presenter.createNewItem()
        }
        val removeTouchHelper = ItemTouchHelper(ItemSwipeRemoveBehavior())
        val toggleTouchHelper = ItemTouchHelper(ItemSwipeToggleBehavior())
        removeTouchHelper.attachToRecyclerView(items_recycler_view)
        toggleTouchHelper.attachToRecyclerView(items_recycler_view)
        KeyboardVisibilityEvent.setEventListener(
            this
        ) {
            presenter.onKeyboardVisibilityChanged(it)
        }
        presenter.attachView(this)
        presenter.onViewReady()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)
    }

    override fun setItems(items: List<ItemRowModel>) {
        itemsRecyclerViewAdapter.update(items)
    }

    override fun setAddButtonVisible(isVisible: Boolean) {
        button_add.isVisible = isVisible
    }

    override fun clearAllFocus() {
        currentFocus?.clearFocus()
    }
}
