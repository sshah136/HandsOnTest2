package com.sahil.shah.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_stock")
data class CompanyStock (
    @PrimaryKey  val companyName : String,
    @ColumnInfo(name = "opening_price" )val openingPrice: Double,
    @ColumnInfo(name = "closing_price" )val closingPrice: Double
)