package ac.summer.shopmaniac.view

import ac.summer.shopmaniac.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemsRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<ItemViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var data: List<String> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(inflater.inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }
}