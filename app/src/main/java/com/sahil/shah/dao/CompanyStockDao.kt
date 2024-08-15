package com.sahil.shah.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sahil.shah.entity.CompanyStock

@Dao
interface CompanyStockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompanyStock(companyStock: CompanyStock)

    @Query("SELECT * FROM company_stock")
    fun getAllCompanyStocks(): List<CompanyStock>


    @Query("SELECT * FROM company_stock WHERE companyName = :companyName")
    fun getPriceByStockName(companyName: String): CompanyStock?

}