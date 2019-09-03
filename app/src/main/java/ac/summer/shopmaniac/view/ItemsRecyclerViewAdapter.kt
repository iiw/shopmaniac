package ac.summer.shopmaniac.view

import ac.summer.shopmaniac.R
import ac.summer.shopmaniac.domain.ItemRowModel
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemsRecyclerViewAdapter(context: Context) : RecyclerView.Adapter<ItemViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var data: List<ItemRowModel> = listOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(inflater.inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].id?.toLong() ?: 0
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun update(items: List<ItemRowModel>) {
        data = items
        notifyDataSetChanged()
    }
}