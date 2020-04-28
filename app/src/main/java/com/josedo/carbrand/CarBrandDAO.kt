package com.josedo.carbrand

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarBrandDAO {
    @Query("SELECT * FROM ${CarBrand.TABLE_NAME}")
    fun loadCarBrands(): List<CarBrand>

    @Insert
    suspend fun insert(carBrand: CarBrand)

    @Delete
    suspend fun delete(carBrand: CarBrand)

    @Query("DELETE FROM ${CarBrand.TABLE_NAME}")
    fun deleteAll()

}