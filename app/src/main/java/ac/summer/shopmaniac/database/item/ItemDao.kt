package ac.summer.shopmaniac.database.item

import ac.summer.shopmaniac.database.DbProvider
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

    @Insert
    fun insertAll(vararg items: Item)

    @Delete
    fun delete(item: Item)
}