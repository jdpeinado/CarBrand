package com.josedo.carbrand

class BrandRepository(private val carBrandDAO: CarBrandDAO){
    var listBrands: List<CarBrand> = carBrandDAO.loadCarBrands()

    suspend fun insert(carBrand: CarBrand) {
        carBrandDAO.insert(carBrand)
    }

}