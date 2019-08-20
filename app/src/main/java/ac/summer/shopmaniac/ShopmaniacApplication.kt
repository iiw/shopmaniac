package ac.summer.shopmaniac

import ac.summer.shopmaniac.di.appModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShopmaniacApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ShopmaniacApplication)
            modules(appModule)
        }
    }
}