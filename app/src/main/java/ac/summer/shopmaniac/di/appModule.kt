package ac.summer.shopmaniac.di

import ac.summer.shopmaniac.database.DbProvider
import ac.summer.shopmaniac.presenter.ShopmaniacPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { ShopmaniacPresenter() }
    single { DbProvider(androidContext()) }
}