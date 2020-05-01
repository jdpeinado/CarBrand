package com.josedo.carbrand.view.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders

import com.josedo.carbrand.R
import com.josedo.carbrand.model.CarBrand
import com.josedo.carbrand.viewmodel.ShareViewModel
import kotlinx.android.synthetic.main.fragment_data_input.*

/**
 * A simple [Fragment] subclass.
 * Use the [DataInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataInputFragment : DialogFragment() {
    private lateinit var viewModel: ShareViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FulllScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ShareViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        toolbarAddCarBrand.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarAddCarBrand.setTitleTextColor(Color.WHITE)
        toolbarAddCarBrand.setNavigationOnClickListener {
            dismiss()
        }
        toolbarAddCarBrand.title = "Añadir"

        bAdd.setOnClickListener {
            if (checkViewEmpty()) {

                val carBrand: CarBrand = CarBrand(
                    tietBrandName.text.toString(),
                    tietCountry.text.toString(),
                    tietYearFund.text.toString().toInt()
                )
                viewModel.insert(carBrand)
                Toast.makeText(
                    this.parentFragment?.context,
                    "Marca introducida correctamente",
                    Toast.LENGTH_LONG
                ).show()
                dismiss()
            }
        }
    }

    private fun checkViewEmpty(): Boolean {
        var isOk: Boolean = true
        if (tietBrandName.text.toString().isEmpty()) {
            tietBrandName.setError("Nombre de la marca no introducido")
            isOk = false
        }
        if (tietYearFund.text.toString().isEmpty()) {
            tietYearFund.setError("Año de fundación no introducido")
            isOk = false
        }
        if (tietCountry.text.toString().isEmpty()) {
            tietCountry.setError("Pais de origen no introducido")
            isOk = false
        }

        return isOk
    }
}

