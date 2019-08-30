package ac.summer.shopmaniac.view

import ac.summer.shopmaniac.R
import ac.summer.shopmaniac.domain.ItemRowModel
import ac.summer.shopmaniac.presenter.IShopmaniacView
import ac.summer.shopmaniac.presenter.ShopmaniacPresenter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_fullscreen.*
import org.koin.android.ext.android.inject

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ShopmaniacActivity : AppCompatActivity(), IShopmaniacView {
    private val presenter: ShopmaniacPresenter by inject()

    private val mHideHandler = Handler()

    @SuppressLint("InlinedApi")
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        items_recycler_view.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()

    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }
    private lateinit var itemsRecyclerViewAdapter: ItemsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mVisible = true
        itemsRecyclerViewAdapter = ItemsRecyclerViewAdapter(applicationContext)
        items_recycler_view.apply {
            adapter = itemsRecyclerViewAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
        button_add.setOnClickListener {
            presenter.newItem()
        }
        val removeTouchHelper = ItemTouchHelper(ItemSwipeRemoveBehavior())
        val toggleTouchHelper = ItemTouchHelper(ItemSwipeToggleBehavior())
        removeTouchHelper.attachToRecyclerView(items_recycler_view)
        toggleTouchHelper.attachToRecyclerView(items_recycler_view)
        presenter.attachView(this)
        presenter.onViewReady()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    override fun onResume() {
        super.onResume()
        delayedHide(20)
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    override fun setItems(items: List<ItemRowModel>) {
        itemsRecyclerViewAdapter.update(items)
    }

    override fun showKeyboardNewItem() {
        invisible_text.requestFocus()
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}
