package com.josedo.carbrand.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.josedo.carbrand.R
import com.josedo.carbrand.view.adapter.CarBrandAdapter
import com.josedo.carbrand.viewmodel.ShareViewModel
import kotlinx.android.synthetic.main.fragment_car_brand.*

/**
 * A simple [Fragment] subclass.
 */
class CarBrandFragment : Fragment() {
    private lateinit var carBrandAdapter: CarBrandAdapter
    private lateinit var viewModel: ShareViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_brand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(
            ShareViewModel::class.java
        )
        viewModel.refresh()

        carBrandAdapter = CarBrandAdapter()

        rvBrands.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = carBrandAdapter
        }

        observeViewModel()

        floating_action_button.setOnClickListener {
            findNavController().navigate(R.id.dataInputFragment)
        }
    }

    fun observeViewModel() {
        viewModel.listBrands.observe(viewLifecycleOwner, Observer {
            carBrandAdapter.updateData(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if (it != null) {
                if (it)
                    rlBrandProgress.visibility = View.VISIBLE
                else
                    rlBrandProgress.visibility = View.INVISIBLE
            }
        })
    }
}
