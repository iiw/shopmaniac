package ac.summer.shopmaniac.database.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "isChecked") val isChecked: Boolean,
    @ColumnInfo(name = "created") val created: Int
)