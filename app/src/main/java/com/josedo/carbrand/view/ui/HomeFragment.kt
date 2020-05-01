package com.josedo.carbrand.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.josedo.carbrand.R
import com.josedo.carbrand.viewmodel.ShareViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var viewModel: ShareViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ShareViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        tvInfo.text = "Actualmente hay ${viewModel.listBrands.value?.size} marcas registradas"

        viewModel.listBrands.observe(viewLifecycleOwner, Observer {
            tvInfo.text = "Actualmente hay ${viewModel.listBrands.value?.size} marcas registradas"
        })

        floating_action_button.setOnClickListener {
            findNavController().navigate(R.id.dataInputFragment)
        }
    }

}
