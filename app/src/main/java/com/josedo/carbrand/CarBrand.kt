package com.josedo.carbrand

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = CarBrand.TABLE_NAME)
class CarBrand(
    @ColumnInfo(name = "name_brand")
    var nameBrand: String,

    var country: String,

    @ColumnInfo(name = "year_fund")
    var yearFund: Int = 0

): Serializable {
    companion object {
        const val TABLE_NAME = "brand_table"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}