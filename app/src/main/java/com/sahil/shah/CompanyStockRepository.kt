package com.sahil.shah

import android.os.Build.VERSION_CODES.S
import com.sahil.shah.dao.CompanyStockDao
import com.sahil.shah.entity.CompanyStock

class CompanyStockRepository(private val companyStockDao: CompanyStockDao) {

    suspend fun insertCompanyStock(companyStock: CompanyStock) {
        companyStockDao.insertCompanyStock(companyStock)
    }

    suspend fun getPriceByStockName(stockName: String): CompanyStock?{
        return companyStockDao.getPriceByStockName(stockName)
    }

}