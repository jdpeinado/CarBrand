package com.josedo.carbrand.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.josedo.carbrand.model.BrandRepository
import com.josedo.carbrand.model.CarBrand
import com.josedo.carbrand.model.CarBrandRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShareViewModel(application: Application): AndroidViewModel(application) {
    private val repository: BrandRepository
    var listBrands: LiveData<List<CarBrand>>
    var isLoading = MutableLiveData<Boolean>()

    init {
        val entryMeasureDao = CarBrandRoomDatabase.getDatabase(application, viewModelScope).carBrandDAO()
        repository = BrandRepository(entryMeasureDao)
        listBrands = repository.listBrands
        isLoading.value = true
    }

    fun refresh(){
        getListBrands()
    }

    private fun getListBrands(){
        isLoading.value = true
        listBrands = repository.listBrands
        isLoading.value =  false
    }

    fun insert(carBrand: CarBrand) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(carBrand)
    }
}