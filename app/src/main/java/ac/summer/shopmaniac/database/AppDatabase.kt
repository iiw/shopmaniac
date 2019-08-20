package ac.summer.shopmaniac.database

import ac.summer.shopmaniac.database.item.Item
import ac.summer.shopmaniac.database.item.ItemDao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Item::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}