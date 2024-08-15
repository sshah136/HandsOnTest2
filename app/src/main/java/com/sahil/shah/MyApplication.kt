package com.sahil.shah

import android.app.Application

class MyApplication: Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { CompanyStockRepository(database.companyStockDao()) }

}