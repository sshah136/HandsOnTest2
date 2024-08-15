package com.sahil.shah.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sahil.shah.CompanyStockRepository
import com.sahil.shah.entity.CompanyStock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyStockViewModel (private val repository: CompanyStockRepository): ViewModel() {


    fun insertCompanyStock(companyStock: CompanyStock) {
        viewModelScope.launch (Dispatchers.IO){
            repository.insertCompanyStock(companyStock)
        }
    }

    suspend fun getPriceByStockName(stockName: String) = repository.getPriceByStockName(stockName)
}

class CompanyStockViewModelFactory(private val repository: CompanyStockRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompanyStockViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompanyStockViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}