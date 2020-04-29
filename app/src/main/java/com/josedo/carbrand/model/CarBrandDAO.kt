package com.josedo.carbrand.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.josedo.carbrand.model.CarBrand

@Dao
interface CarBrandDAO {
    @Query("SELECT * FROM ${CarBrand.TABLE_NAME}")
    fun loadCarBrands(): LiveData<List<CarBrand>>

    @Insert
    suspend fun insert(carBrand: CarBrand)

    @Delete
    suspend fun delete(carBrand: CarBrand)

    @Query("DELETE FROM ${CarBrand.TABLE_NAME}")
    fun deleteAll()

}