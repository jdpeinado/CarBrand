package com.josedo.carbrand.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.josedo.carbrand.R
import com.josedo.carbrand.view.adapter.CarBrandAdapter
import com.josedo.carbrand.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var carBrandAdapter: CarBrandAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(
            MainViewModel::class.java
        )
        mainViewModel.refresh()

        carBrandAdapter = CarBrandAdapter()

        rvBrands.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = carBrandAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        mainViewModel.listBrands.observe(this, Observer {
            carBrandAdapter.updateData(it)
        })

        mainViewModel.isLoading.observe(this, Observer<Boolean> {
            if (it != null) {
                if (it)
                    rlBrandProgress.visibility = View.VISIBLE
                else
                    rlBrandProgress.visibility = View.INVISIBLE
            }
        })
    }
}
