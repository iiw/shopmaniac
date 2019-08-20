package ac.summer.shopmaniac.database

import ac.summer.shopmaniac.database.item.Item
import ac.summer.shopmaniac.database.item.ItemDao
import android.content.Context
import androidx.room.*

class DbProvider(context: Context) {
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "shopmaniac"
        ).build()
    }

    fun getDatabase(): AppDatabase = db
}