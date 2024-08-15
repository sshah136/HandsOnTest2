package com.sahil.shah

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sahil.shah.dao.CompanyStockDao
import com.sahil.shah.entity.CompanyStock

@Database(entities = [CompanyStock::class], version = 1,exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyStockDao(): CompanyStockDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            if(INSTANCE == null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "company_stock"
                    ).allowMainThreadQueries().build()
                }

            }
            return  INSTANCE!!
        }
    }
}