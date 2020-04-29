package com.josedo.carbrand.model

import androidx.lifecycle.LiveData
import com.josedo.carbrand.model.CarBrand
import com.josedo.carbrand.model.CarBrandDAO

class BrandRepository(private val carBrandDAO: CarBrandDAO){
    var listBrands: LiveData<List<CarBrand>> = carBrandDAO.loadCarBrands()

    suspend fun insert(carBrand: CarBrand) {
        carBrandDAO.insert(carBrand)
    }

}